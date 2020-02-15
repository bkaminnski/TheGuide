package com.softwarewithpassion.electricityplansimproved.readingvalues.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadingValue that = (ReadingValue) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }
}
