package com.softwarewithpassion.electricityplans.readingvalues.control;

import com.softwarewithpassion.electricityplans.readingvalues.entity.ReadingValue;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ONE;

@Component
public class ReadingValuesGenerator {

    public List<ReadingValue> generateReadingValues(LocalDateTime sinceClosed, LocalDateTime untilOpen, int meterSerialNumber) {
        List<ReadingValue> readingValues = new ArrayList<>();
        LocalDateTime timestamp = sinceClosed;
        while (timestamp.isBefore(untilOpen)) {
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
