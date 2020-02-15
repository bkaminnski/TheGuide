package com.softwarewithpassion.electricityplans.invoices.control;

import com.softwarewithpassion.electricityplans.readingvalues.control.ReadingValuesGenerator;
import com.softwarewithpassion.electricityplans.readingvalues.entity.ReadingValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadingValuesGeneratorSpy extends ReadingValuesGenerator {
    private LocalDateTime sinceClosed;
    private LocalDateTime untilOpen;
    private int meterSerialNumber;

    @Override
    public List<ReadingValue> generateReadingValues(LocalDateTime sinceClosed, LocalDateTime untilOpen, int meterSerialNumber) {
        this.sinceClosed = sinceClosed;
        this.untilOpen = untilOpen;
        this.meterSerialNumber = meterSerialNumber;
        return new ArrayList<>();
    }

    public LocalDateTime getSinceClosed() {
        return sinceClosed;
    }

    public LocalDateTime getUntilOpen() {
        return untilOpen;
    }

    public int getMeterSerialNumber() {
        return meterSerialNumber;
    }
}
