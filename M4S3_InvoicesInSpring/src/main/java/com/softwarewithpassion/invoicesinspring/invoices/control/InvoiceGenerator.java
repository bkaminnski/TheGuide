package com.softwarewithpassion.invoicesinspring.invoices.control;

import com.softwarewithpassion.invoicesinspring.invoices.entity.Invoice;
import com.softwarewithpassion.invoicesinspring.invoices.entity.InvoiceLine;
import com.softwarewithpassion.invoicesinspring.readingvalues.control.ReadingValuesGenerator;
import com.softwarewithpassion.invoicesinspring.readingvalues.entity.ReadingValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InvoiceGenerator {
    private final ReadingValuesGenerator readingValuesGenerator;

    @Autowired
    public InvoiceGenerator(ReadingValuesGenerator readingValuesGenerator) {
        this.readingValuesGenerator = readingValuesGenerator;
    }

    public Invoice generateInvoice(LocalDateTime sinceClosed, LocalDateTime untilOpen, int meterSerialNumber) {
        List<ReadingValue> readingValues = readingValuesGenerator.generateReadingValues(sinceClosed, untilOpen, meterSerialNumber);
        NightPlan nightPlan = new NightPlan();
        nightPlan.addReadingValues(readingValues);
        List<InvoiceLine> invoiceLines = nightPlan.toInvoiceLines();
        return new Invoice(invoiceLines);
    }
}
