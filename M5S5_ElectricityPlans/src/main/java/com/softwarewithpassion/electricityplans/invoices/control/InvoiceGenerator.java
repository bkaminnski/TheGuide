package com.softwarewithpassion.electricityplans.invoices.control;

import com.softwarewithpassion.electricityplans.invoices.entity.Invoice;
import com.softwarewithpassion.electricityplans.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplans.readingvalues.control.ReadingValuesGenerator;
import com.softwarewithpassion.electricityplans.readingvalues.entity.ReadingValue;
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

    public Invoice generateInvoice(LocalDateTime sinceClosed, LocalDateTime untilOpen, int meterSerialNumber, ElectricityPlan electricityPlan) {
        List<ReadingValue> readingValues = readingValuesGenerator.generateReadingValues(sinceClosed, untilOpen, meterSerialNumber);
        electricityPlan.addReadingValues(readingValues);
        List<InvoiceLine> invoiceLines = electricityPlan.toInvoiceLines();
        return new Invoice(invoiceLines);
    }
}
