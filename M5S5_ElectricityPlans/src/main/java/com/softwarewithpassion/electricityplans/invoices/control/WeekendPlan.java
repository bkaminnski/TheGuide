package com.softwarewithpassion.electricityplans.invoices.control;

import com.softwarewithpassion.electricityplans.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplans.readingvalues.entity.ReadingValue;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class WeekendPlan implements ElectricityPlan {
    private static final BigDecimal WORKING_DAY_RATE = new BigDecimal("0.19288");
    private static final BigDecimal WEEKEND_RATE = new BigDecimal("0.17531");
    private BigDecimal workingDayConsumption = ZERO;
    private BigDecimal weekendConsumption = ZERO;

    @Override
    public void addReadingValues(List<ReadingValue> readingValues) {
        readingValues.forEach(this::addReadingValue);
    }

    private void addReadingValue(ReadingValue readingValue) {
        if (readingValue.getTimestamp().getDayOfWeek() == SATURDAY || readingValue.getTimestamp().getDayOfWeek() == SUNDAY) {
            this.weekendConsumption = this.weekendConsumption.add(readingValue.getValue());
        } else {
            this.workingDayConsumption = this.workingDayConsumption.add(readingValue.getValue());
        }
    }

    @Override
    public List<InvoiceLine> toInvoiceLines() {
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        invoiceLines.add(new InvoiceLine("Working Day Rate", WORKING_DAY_RATE, workingDayConsumption, WORKING_DAY_RATE.multiply(workingDayConsumption)));
        invoiceLines.add(new InvoiceLine("Weekend Rate", WEEKEND_RATE, weekendConsumption, WEEKEND_RATE.multiply(weekendConsumption)));
        return invoiceLines;
    }
}
