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

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Limit order.
 *
 * <p>An order has a side (buy/sell), quantity and price. For example: buy 10 units @ 5 SEK each (or better price).
 */
public class Order {
    private static final Pattern PATTERN =
            Pattern.compile("(?<side>([bB][uU][yY])|([sS][eE][lL][lL]))[ ]+(?<qty>[0-9]+)[ ]*@[ ]*(?<px>[0-9]+)([ ]+#(?<id>[0-9]+))?");
    private static final String GROUP_ID = "id";
    private static final String GROUP_SIDE = "side";
    private static final String GROUP_QUANTITY = "qty";
    private static final String GROUP_PRICE = "px";

    private final long id;
    private final Side side;
    private final long price;
    private long quantity;

    /**
     * Create a new order.
     * @param id the client assigned id.
     * @param side the side (buy/sell), not null.
     * @param price the price, must be &gt;= 0.
     * @param quantity the quantity, must be &gt;= 0.
     */
    public Order(long id, Side side, long price, long quantity) {
        this.id = id;
        this.side = Objects.requireNonNull(side);
        if (price < 0) {
            throw new IllegalArgumentException("price must be >= 0");
        }
        this.price = price;
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        this.quantity = quantity;
    }

    /**
     * Returns the client assigned order id.
     * @return the client assigned order id.
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the side.
     * @return the side, not null.
     */
    public Side getSide() {
        return side;
    }

    /**
     * Returns the price.
     * @return the price.
     */
    public long getPrice() {
        return price;
    }

    /**
     * Returns the quantity.
     * @return the quantity.
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity.
     * @param quantity the new quantity, must be &gt;= 0.
     */
    public void setQuantity(long quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity must be >= 0");
        }
        this.quantity = quantity;
    }

    /**
     * Returns true if the quantity is zero.
     * @return true if the quantity is zero, otherwise false.
     */
    public boolean isEmpty() {
        return quantity == 0;
    }

    @Override
    public String toString() {
        return side + " " + quantity + "@" + price + " #" + id;
    }

    public static Order parse(String str) {
        Matcher m = PATTERN.matcher(str);
        if (!m.matches()) {
            throw new IllegalArgumentException("Illegal order format. Expected #id buy|sell quantity@price");
        }
        String idStr = m.group(GROUP_ID);
        long id = idStr != null ? Long.valueOf(idStr) : 0L;
        Side side = Side.valueOf(m.group(GROUP_SIDE).toUpperCase());
        long price = Long.valueOf(m.group(GROUP_PRICE));
        long quantity = Long.valueOf(m.group(GROUP_QUANTITY));

        return new Order(id, side, price, quantity);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.side);
        hash = 79 * hash + (int) (this.price ^ (this.price >>> 32));
        hash = 79 * hash + (int) (this.quantity ^ (this.quantity >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.side != other.side) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }

    

}
