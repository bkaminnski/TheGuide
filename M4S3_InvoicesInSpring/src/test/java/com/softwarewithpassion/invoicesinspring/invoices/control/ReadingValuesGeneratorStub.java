package com.softwarewithpassion.invoicesinspring.invoices.control;


import com.softwarewithpassion.invoicesinspring.readingvalues.control.ReadingValuesGenerator;
import com.softwarewithpassion.invoicesinspring.readingvalues.entity.ReadingValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReadingValuesGeneratorStub extends ReadingValuesGenerator {

    @Override
    public List<ReadingValue> generateReadingValues(LocalDateTime sinceClosed, LocalDateTime untilOpen, int meterSerialNumber) {
        return List.of(
                new ReadingValue(new BigDecimal(100), LocalDateTime.parse("2020-01-01T00:00:00")),
                new ReadingValue(new BigDecimal(100), LocalDateTime.parse("2020-01-01T15:00:00"))
        );
    }
}
