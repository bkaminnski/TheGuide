package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;

import java.math.BigDecimal;
import java.util.List;

public class ElectricityPlanStub implements ElectricityPlan, InvoiceablePlan {
    @Override
    public InvoiceablePlan addReadingValues(List<ReadingValue> readingValues) {
        return this;
    }

    @Override
    public List<InvoiceLine> toInvoiceLines() {
        return List.of(
                new InvoiceLine("First", new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)),
                new InvoiceLine("Second", new BigDecimal(4), new BigDecimal(5), new BigDecimal(6))
        );
    }
}
