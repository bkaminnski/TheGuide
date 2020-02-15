package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.invoices.entity.Invoice;
import com.softwarewithpassion.electricityplansimproved.invoices.entity.InvoiceLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.softwarewithpassion.electricityplansimproved.RecursiveComparisonConfigurations.comparingBigDecimalsWithComparator;
import static org.assertj.core.api.Assertions.assertThat;

class InvoiceGeneratorTest {
    private static final Period FIRST_WEEK_OF_2020 = new Period(LocalDateTime.parse("2020-01-01T00:00:00"), LocalDateTime.parse("2020-01-08T00:00:00"));
    private static final int METER_SERIAL_NUMBER = 4512;

    @Test
    void shouldGenerateInvoice() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(new ReadingValuesGeneratorStub());

        Invoice invoice = invoiceGenerator.generateInvoice(FIRST_WEEK_OF_2020, METER_SERIAL_NUMBER, new ElectricityPlanStub());

        assertThat(invoice).usingRecursiveComparison(comparingBigDecimalsWithComparator()).isEqualTo(expectedInvoice());
    }

    private Invoice expectedInvoice() {
        return new Invoice(List.of(
                new InvoiceLine("First", new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)),
                new InvoiceLine("Second", new BigDecimal(4), new BigDecimal(5), new BigDecimal(6))
        ));
    }

    @Test
    void shouldGenerateReadingValuesBasedOnTheSameParameters() {
        ReadingValuesGeneratorSpy readingValuesGenerator = new ReadingValuesGeneratorSpy();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(readingValuesGenerator);

        invoiceGenerator.generateInvoice(FIRST_WEEK_OF_2020, METER_SERIAL_NUMBER, new NightPlan());

        assertThat(readingValuesGenerator.getPeriod()).isEqualTo(FIRST_WEEK_OF_2020);
        assertThat(readingValuesGenerator.getMeterSerialNumber()).isEqualTo(METER_SERIAL_NUMBER);
    }
}