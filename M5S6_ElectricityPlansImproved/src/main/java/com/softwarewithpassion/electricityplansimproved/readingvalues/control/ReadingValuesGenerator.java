package com.softwarewithpassion.electricityplansimproved.readingvalues.control;

import com.softwarewithpassion.electricityplansimproved.invoices.control.Period;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ONE;

@Component
public class ReadingValuesGenerator {

    public List<ReadingValue> generateReadingValues(Period period, int meterSerialNumber) {
        List<ReadingValue> readingValues = new ArrayList<>();
        LocalDateTime timestamp = period.getSinceClosed();
        while (period.contains(timestamp)) {
            ReadingValue readingValue = generateReadingValue(timestamp, meterSerialNumber);
            readingValues.add(readingValue);
            timestamp = timestamp.plusHours(1);
        }
        return readingValues;
    }

    private ReadingValue generateReadingValue(LocalDateTime timestamp, int meterSerialNumber) {
        BigDecimal value = meterSerialNumber % 2 == 0 ? new BigDecimal("0.5") : ONE;
        return new ReadingValue(value, timestamp);
    }
}
