package com.softwarewithpassion.electricityplansimproved.readingvalues.control;

import com.softwarewithpassion.electricityplansimproved.invoices.control.Period;
import com.softwarewithpassion.electricityplansimproved.readingvalues.entity.ReadingValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReadingValuesGeneratorTest {
    private static final Period FIRST_THREE_HOURS_OF_2020 = new Period(LocalDateTime.parse("2020-01-01T00:00:00"), LocalDateTime.parse("2020-01-01T03:00:00"));
    private static final int ODD_METER_ID = 1;
    private static final int EVEN_METER_ID = 2;
    private ReadingValuesGenerator readingValuesGenerator;

    @BeforeEach
    void setUp() {
        readingValuesGenerator = new ReadingValuesGenerator();
    }

    @Test
    void shouldGenerateReadingValuesForOddMeterId() {
        List<ReadingValue> readingValues = readingValuesGenerator.generateReadingValues(FIRST_THREE_HOURS_OF_2020, ODD_METER_ID);

        assertThat(readingValues).containsExactly(
                expectedReadingValues(new BigDecimal("1"))
        );
    }

    @Test
    void shouldGenerateReadingValuesForEvenMeterId() {
        List<ReadingValue> readingValues = readingValuesGenerator.generateReadingValues(FIRST_THREE_HOURS_OF_2020, EVEN_METER_ID);

        assertThat(readingValues).containsExactly(expectedReadingValues(new BigDecimal("0.5")));
    }

    private ReadingValue[] expectedReadingValues(BigDecimal value) {
        return new ReadingValue[]{
                new ReadingValue(value, LocalDateTime.parse("2020-01-01T00:00:00")),
                new ReadingValue(value, LocalDateTime.parse("2020-01-01T01:00:00")),
                new ReadingValue(value, LocalDateTime.parse("2020-01-01T02:00:00"))
        };
    }
}