package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.invoices.entity.InvoiceLine;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NightPlan implements ElectricityPlan, InvoiceablePlan {
    private static final BigDecimal DAY_RATE = new BigDecimal("0.19288");
    private static final BigDecimal NIGHT_RATE = new BigDecimal("0.17531");
    private static final int DAY_START = 8;
    private static final int NIGHT_START = 23;
    private final BigDecimal dayConsumption;
    private final BigDecimal nightConsumption;

    public NightPlan() {
        dayConsumption = BigDecimal.ZERO;
        nightConsumption = BigDecimal.ZERO;
    }

    private NightPlan(BigDecimal dayConsumption, BigDecimal nightConsumption) {
        this.dayConsumption = dayConsumption;
        this.nightConsumption = nightConsumption;
    }

    @Override
    public InvoiceablePlan addReadingValues(List<ReadingValue> readingValues) {
        BigDecimal dayConsumption = calculateDayConsumption(readingValues);
        BigDecimal nightConsumption = calculateNightConsumption(readingValues);
        return new NightPlan(dayConsumption, nightConsumption);
    }

    private BigDecimal calculateDayConsumption(List<ReadingValue> readingValues) {
        return readingValues.stream()
                .filter(this::readingValueDuringDay)
                .map(ReadingValue::getValue)
                .reduce(this.dayConsumption, BigDecimal::add);
    }

    private BigDecimal calculateNightConsumption(List<ReadingValue> readingValues) {
        return readingValues.stream()
                .filter(r -> !readingValueDuringDay(r))
                .map(ReadingValue::getValue)
                .reduce(this.nightConsumption, BigDecimal::add);
    }

    private boolean readingValueDuringDay(ReadingValue r) {
        return r.getTimestamp().getHour() >= DAY_START && r.getTimestamp().getHour() < NIGHT_START;
    }

    @Override
    public List<InvoiceLine> toInvoiceLines() {
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        invoiceLines.add(new InvoiceLine("Day Rate", DAY_RATE, dayConsumption, DAY_RATE.multiply(dayConsumption)));
        invoiceLines.add(new InvoiceLine("Night Rate", NIGHT_RATE, nightConsumption, NIGHT_RATE.multiply(nightConsumption)));
        return invoiceLines;
    }
}
