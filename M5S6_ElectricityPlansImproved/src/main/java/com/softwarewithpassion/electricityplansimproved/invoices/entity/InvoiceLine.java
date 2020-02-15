package com.softwarewithpassion.electricityplansimproved.invoices.entity;

import java.math.BigDecimal;

public class InvoiceLine {
    private final String description;
    private final BigDecimal rate;
    private final BigDecimal energyConsumed;
    private final BigDecimal totalAmount;

    public InvoiceLine(String description, BigDecimal rate, BigDecimal energyConsumed, BigDecimal totalAmount) {
        this.description = description;
        this.rate = rate;
        this.energyConsumed = energyConsumed;
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getEnergyConsumed() {
        return energyConsumed;
    }
}
