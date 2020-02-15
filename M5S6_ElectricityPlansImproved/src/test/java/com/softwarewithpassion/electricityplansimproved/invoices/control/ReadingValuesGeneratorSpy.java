package com.softwarewithpassion.electricityplansimproved.invoices.control;

import com.softwarewithpassion.electricityplansimproved.readingvalues.control.ReadingValuesGenerator;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;

import java.util.ArrayList;
import java.util.List;

public class ReadingValuesGeneratorSpy extends ReadingValuesGenerator {
    private Period period;
    private int meterSerialNumber;

    @Override
    public List<ReadingValue> generateReadingValues(Period period, int meterSerialNumber) {
        this.period = period;
        this.meterSerialNumber = meterSerialNumber;
        return new ArrayList<>();
    }

    public Period getPeriod() {
        return period;
    }

    public int getMeterSerialNumber() {
        return meterSerialNumber;
    }
}
