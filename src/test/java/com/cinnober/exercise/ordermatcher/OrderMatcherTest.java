/*
 * Copyright (c) 2014 Cinnober Financial Technology AB, Stockholm,
 * Sweden. All rights reserved.
 * 
 * This software is the confidential and proprietary information of
 * Cinnober Financial Technology AB, Stockholm, Sweden. You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Cinnober.
 * 
 * Cinnober makes no representations or warranties about the suitability
 * of the software, either expressed or implied, including, but not limited
 * to, the implied warranties of merchantibility, fitness for a particular
 * purpose, or non-infringement. Cinnober shall not be liable for any
 * damages suffered by licensee as a result of using, modifying, or
 * distributing this software or its derivatives.
 */

package com.cinnober.exercise.ordermatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikael.brannstrom
 */
public class OrderMatcherTest {

    public OrderMatcherTest() {
    }

    @Test
    public void testMatchPassiveSetsThePrice1() {
        testMatch(
            Arrays.asList("BUY 100@10 #1", "SELL 100@9 #2"),
            Arrays.asList("TRADE 100@10 (#2/#1)"),
            Arrays.asList()
        );
    }

    @Test
    public void testMatchPassiveSetsThePrice2() {
        testMatch(
            Arrays.asList("SELL 100@10 #1", "BUY 100@11 #2"),
            Arrays.asList("TRADE 100@10 (#2/#1)"),
            Arrays.asList()
        );
    }

    @Test
    public void testMatchTimePriority1() {
        testMatch(
            Arrays.asList("BUY 50@10 #1", "BUY 100@10 #2", "SELL 150@10 #3"),
            Arrays.asList("TRADE 50@10 (#3/#1)", "TRADE 100@10 (#3/#2)"),
            Arrays.asList()
        );
    }

    @Test
    public void testMatchTimePriority2() {
        testMatch(
            Arrays.asList("SELL 50@10 #1", "SELL 100@10 #2", "BUY 150@10 #3"),
            Arrays.asList("TRADE 50@10 (#3/#1)", "TRADE 100@10 (#3/#2)"),
            Arrays.asList()
        );
    }

    @Test
    public void testMatchPricePriority1() {
        testMatch(
            Arrays.asList("SELL 50@11 #1", "SELL 100@10 #2", "BUY 150@10 #3"),
            Arrays.asList("TRADE 100@10 (#3/#2)"),
            Arrays.asList("BUY 50@10 #3", "SELL 50@11 #1")
        );
    }

    @Test
    public void testMatchPricePriority2() {
        testMatch(
            Arrays.asList("BUY 50@9 #1", "BUY 100@10 #2", "SELL 150@10 #3"),
            Arrays.asList("TRADE 100@10 (#3/#2)"),
            Arrays.asList("BUY 50@9 #1", "SELL 50@10 #3")
        );
    }
    
    private void testMatch(List<String> inputOrders, List<String> expTrades, List<String> expRemainingOrders) {
        OrderMatcher matcher = new OrderMatcher();
        ArrayList<Trade> trades = new ArrayList<>();
        // add orders
        inputOrders.stream().map(s -> Order.parse(s)).forEach(o -> trades.addAll(matcher.addOrder(o)));

        // verify trades
        assertEquals(expTrades, trades.stream().map(t -> t.toString()).collect(Collectors.toList()));

        // verify remaining orders
        assertEquals(expRemainingOrders,
                     Stream.concat(matcher.getOrders(Side.BUY).stream(), matcher.getOrders(Side.SELL).stream()).
                             map(o -> o.toString()).collect(Collectors.toList()));
    }

}
