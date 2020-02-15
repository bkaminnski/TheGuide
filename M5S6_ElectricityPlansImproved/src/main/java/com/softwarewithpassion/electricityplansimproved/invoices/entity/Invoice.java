package com.softwarewithpassion.electricityplansimproved.invoices.entity;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class Invoice {
    private final BigDecimal totalAmount;
    private final List<InvoiceLine> invoiceLines;

    public Invoice(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
        this.totalAmount = invoiceLines.stream()
                .map(i -> i.getTotalAmount())
                .reduce(ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }
}
