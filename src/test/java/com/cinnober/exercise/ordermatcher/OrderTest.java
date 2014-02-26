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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikael.brannstrom
 */
public class OrderTest {

    public OrderTest() {
    }

    @Test
    public void testParse() {
        assertEquals(new Order(1, Side.BUY, 5, 100), Order.parse("BUY 100@5 #1"));
        assertEquals(new Order(1, Side.BUY, 5, 100), Order.parse("buy 100@5 #1"));
        assertEquals(new Order(0, Side.BUY, 5, 100), Order.parse("buy 100@5"));
        assertEquals(new Order(0, Side.BUY, 5, 100), Order.parse("buy 100@5 #0"));
        assertEquals(new Order(1, Side.SELL, 5, 100), Order.parse("SELL 100@5 #1"));
        assertEquals(new Order(1, Side.SELL, 5, 100), Order.parse("sell 100@5 #1"));
    }

    @Test
    public void testToString() {
        assertEquals("BUY 100@5 #1", new Order(1, Side.BUY, 5, 100).toString());
        assertEquals("BUY 100@5 #0", new Order(0, Side.BUY, 5, 100).toString());
        assertEquals("SELL 100@5 #1", new Order(1, Side.SELL, 5, 100).toString());
    }


}
