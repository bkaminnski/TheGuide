package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;

import java.util.List;

public interface ElectricityPlan {
    InvoiceablePlan addReadingValues(List<ReadingValue> readingValues);
}
