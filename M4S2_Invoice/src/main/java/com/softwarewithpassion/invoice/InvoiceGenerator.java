package com.softwarewithpassion.invoice;

import com.softwarewithpassion.readingvalues.ReadingValue;
import com.softwarewithpassion.readingvalues.ReadingValuesGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceGenerator {
    private final ReadingValuesGenerator readingValuesGenerator;

    public InvoiceGenerator(ReadingValuesGenerator readingValuesGenerator) {
        this.readingValuesGenerator = readingValuesGenerator;
    }

    public Invoice generateInvoice(LocalDateTime sinceClosed, LocalDateTime untilClosed, int meterSerialNumber) {
        List<ReadingValue> readingValues = readingValuesGenerator.generateReadingValues(sinceClosed, untilClosed, meterSerialNumber);
        NightPlan nightPlan = new NightPlan();
        nightPlan.addReadingValues(readingValues);
        List<InvoiceLine> invoiceLines = nightPlan.toInvoiceLines();
        return new Invoice(invoiceLines);
    }
}
