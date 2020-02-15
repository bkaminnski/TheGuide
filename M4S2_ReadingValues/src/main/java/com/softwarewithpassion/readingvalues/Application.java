package com.softwarewithpassion.readingvalues;

import java.time.LocalDateTime;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        LocalDateTime sinceClosed = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime untilOpen = LocalDateTime.parse("2020-01-08T00:00:00");
        int meterSerialNumber = 4512;

        ReadingValuesGenerator readingValuesGenerator = new ReadingValuesGenerator();
        List<ReadingValue> readingValues = readingValuesGenerator.generateReadingValues(sinceClosed, untilOpen, meterSerialNumber);
        readingValues.forEach(System.out::println);
    }
}
