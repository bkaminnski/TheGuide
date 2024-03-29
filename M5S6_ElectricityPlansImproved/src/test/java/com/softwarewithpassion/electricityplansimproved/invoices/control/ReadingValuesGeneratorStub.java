package com.softwarewithpassion.electricityplansimproved.invoices.control;


import com.softwarewithpassion.electricityplansimproved.readingvalues.control.ReadingValuesGenerator;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReadingValuesGeneratorStub extends ReadingValuesGenerator {

    @Override
    public List<ReadingValue> generateReadingValues(Period period, int meterSerialNumber) {
        return List.of(
                new ReadingValue(new BigDecimal(100), LocalDateTime.parse("2020-01-01T00:00:00")),
                new ReadingValue(new BigDecimal(100), LocalDateTime.parse("2020-01-01T15:00:00"))
        );
    }
}
