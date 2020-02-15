package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class WeekendPlan implements ElectricityPlan, InvoiceablePlan {
    private static final BigDecimal WORKING_DAY_RATE = new BigDecimal("0.19288");
    private static final BigDecimal WEEKEND_RATE = new BigDecimal("0.17531");
    private final BigDecimal workingDayConsumption;
    private final BigDecimal weekendConsumption;

    public WeekendPlan() {
        this.workingDayConsumption = ZERO;
        this.weekendConsumption = ZERO;
    }

    private WeekendPlan(BigDecimal workingDayConsumption, BigDecimal weekendConsumption) {
        this.workingDayConsumption = workingDayConsumption;
        this.weekendConsumption = weekendConsumption;
    }

    @Override
    public InvoiceablePlan addReadingValues(List<ReadingValue> readingValues) {
        BigDecimal workingDayConsumption = calculateWorkingDayConsumption(readingValues);
        BigDecimal weekendConsumption = calculateWeekendConsumption(readingValues);
        return new WeekendPlan(workingDayConsumption, weekendConsumption);
    }

    private BigDecimal calculateWorkingDayConsumption(List<ReadingValue> readingValues) {
        return readingValues.stream()
                .filter(r -> !readingValueDuringWeekend(r))
                .map(ReadingValue::getValue)
                .reduce(this.workingDayConsumption, BigDecimal::add);
    }

    private BigDecimal calculateWeekendConsumption(List<ReadingValue> readingValues) {
        return readingValues.stream()
                .filter(this::readingValueDuringWeekend)
                .map(ReadingValue::getValue)
                .reduce(this.weekendConsumption, BigDecimal::add);
    }

    private boolean readingValueDuringWeekend(ReadingValue r) {
        return r.getTimestamp().getDayOfWeek() == SATURDAY || r.getTimestamp().getDayOfWeek() == SUNDAY;
    }

    @Override
    public List<InvoiceLine> toInvoiceLines() {
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        invoiceLines.add(new InvoiceLine("Working Day Rate", WORKING_DAY_RATE, workingDayConsumption, WORKING_DAY_RATE.multiply(workingDayConsumption)));
        invoiceLines.add(new InvoiceLine("Weekend Rate", WEEKEND_RATE, weekendConsumption, WEEKEND_RATE.multiply(weekendConsumption)));
        return invoiceLines;
    }
}
