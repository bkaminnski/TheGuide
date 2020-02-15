package com.softwarewithpassion.electricityplans.invoices.control;

import com.softwarewithpassion.electricityplans.invoices.entity.Invoice;
import com.softwarewithpassion.electricityplans.invoices.entity.InvoiceLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.softwarewithpassion.electricityplans.RecursiveComparisonConfigurations.comparingBigDecimalsWithComparator;
import static org.assertj.core.api.Assertions.assertThat;

class InvoiceGeneratorTest {
    private static final LocalDateTime SINCE_CLOSED = LocalDateTime.parse("2020-01-01T00:00:00");
    private static final LocalDateTime UNTIL_OPEN = LocalDateTime.parse("2020-01-08T00:00:00");
    private static final int METER_SERIAL_NUMBER = 4512;
    private static final ElectricityPlanStub ELECTRICITY_PLAN = new ElectricityPlanStub();

    @Test
    void shouldGenerateInvoice() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(new ReadingValuesGeneratorStub());

        Invoice invoice = invoiceGenerator.generateInvoice(SINCE_CLOSED, UNTIL_OPEN, METER_SERIAL_NUMBER, ELECTRICITY_PLAN);

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

        invoiceGenerator.generateInvoice(SINCE_CLOSED, UNTIL_OPEN, METER_SERIAL_NUMBER, ELECTRICITY_PLAN);

        assertThat(readingValuesGenerator.getSinceClosed()).isEqualTo(SINCE_CLOSED);
        assertThat(readingValuesGenerator.getUntilOpen()).isEqualTo(UNTIL_OPEN);
        assertThat(readingValuesGenerator.getMeterSerialNumber()).isEqualTo(METER_SERIAL_NUMBER);
    }
}