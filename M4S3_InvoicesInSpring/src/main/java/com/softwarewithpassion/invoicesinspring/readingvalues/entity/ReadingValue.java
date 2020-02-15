package com.softwarewithpassion.invoicesinspring.readingvalues.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReadingValue {
    private final BigDecimal value;
    private final LocalDateTime timestamp;

    public ReadingValue(BigDecimal value, LocalDateTime timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ReadingValue{" +
                "value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
