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

/**
 * A matched trade.
 */
public class Trade {
    private final long activeOrderId;
    private final long passiveOrderId;
    private final long price;
    private final long quantity;

    /**
     * Create a new trade.
     * @param activeOrderId the active client assigned order id.
     * @param passiveOrderId the passive client assigned order id.
     * @param price the price.
     * @param quantity the quantity.
     */
    public Trade(long activeOrderId, long passiveOrderId, long price, long quantity) {
        this.activeOrderId = activeOrderId;
        this.passiveOrderId = passiveOrderId;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Returns the active client assigned order id.
     * @return the active client assigned order id.
     */
    public long getActiveOrderId() {
        return activeOrderId;
    }

    /**
     * Returns the passive client assigned order id.
     * @return the passive client assigned order id.
     */
    public long getPassiveOrderId() {
        return passiveOrderId;
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

    @Override
    public String toString() {
        return "Trade " + quantity + "@" + price + " (#" + activeOrderId + "/#" + passiveOrderId + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.activeOrderId ^ (this.activeOrderId >>> 32));
        hash = 47 * hash + (int) (this.passiveOrderId ^ (this.passiveOrderId >>> 32));
        hash = 47 * hash + (int) (this.price ^ (this.price >>> 32));
        hash = 47 * hash + (int) (this.quantity ^ (this.quantity >>> 32));
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
        final Trade other = (Trade) obj;
        if (this.activeOrderId != other.activeOrderId) {
            return false;
        }
        if (this.passiveOrderId != other.passiveOrderId) {
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
