package com.softwarewithpassion.electricityplans.invoices.control;

import com.softwarewithpassion.electricityplans.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplans.readingvalues.entity.ReadingValue;

import java.util.List;

public interface ElectricityPlan {
    void addReadingValues(List<ReadingValue> readingValues);

    List<InvoiceLine> toInvoiceLines();
}
