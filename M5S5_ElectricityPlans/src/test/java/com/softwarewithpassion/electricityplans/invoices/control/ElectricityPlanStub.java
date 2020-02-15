package com.softwarewithpassion.electricityplans.invoices.control;

import com.softwarewithpassion.electricityplans.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplans.readingvalues.entity.ReadingValue;

import java.math.BigDecimal;
import java.util.List;

public class ElectricityPlanStub implements ElectricityPlan {
    @Override
    public void addReadingValues(List<ReadingValue> readingValues) {

    }

    @Override
    public List<InvoiceLine> toInvoiceLines() {
        return List.of(
                new InvoiceLine("First", new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)),
                new InvoiceLine("Second", new BigDecimal(4), new BigDecimal(5), new BigDecimal(6))
        );
    }
}
