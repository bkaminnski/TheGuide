package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.invoices.entity.Invoice;
import com.softwarewithpassion.electricityplansimproved.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplansimproved.readingvalues.control.ReadingValuesGenerator;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceGenerator {
    private final ReadingValuesGenerator readingValuesGenerator;

    @Autowired
    public InvoiceGenerator(ReadingValuesGenerator readingValuesGenerator) {
        this.readingValuesGenerator = readingValuesGenerator;
    }

    public Invoice generateInvoice(Period period, int meterSerialNumber, ElectricityPlan electricityPlan) {
        List<ReadingValue> readingValues = readingValuesGenerator.generateReadingValues(period, meterSerialNumber);
        InvoiceablePlan invoiceablePlan = electricityPlan.addReadingValues(readingValues);
        List<InvoiceLine> invoiceLines = invoiceablePlan.toInvoiceLines();
        return new Invoice(invoiceLines);
    }
}
