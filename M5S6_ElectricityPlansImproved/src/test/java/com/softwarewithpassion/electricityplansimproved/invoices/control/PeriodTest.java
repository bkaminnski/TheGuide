package com.softwarewithpassion.electricityplansimproved.invoices.control;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PeriodTest {

    @ParameterizedTest
    @MethodSource("parameters")
    void shouldDetermineWhetherPeriodContainsTimestamp(LocalDateTime timestamp, boolean expectedContains) {
        Period period = new Period(LocalDateTime.parse("2020-01-01T00:00:00"), LocalDateTime.parse("2020-01-10T00:00:00"));

        boolean contains = period.contains(timestamp);

        assertThat(contains).isEqualTo(expectedContains);
    }

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(LocalDateTime.parse("2019-01-01T00:00:00"), false),
                Arguments.of(LocalDateTime.parse("2020-01-01T00:00:00"), true),
                Arguments.of(LocalDateTime.parse("2020-01-05T00:00:00"), true),
                Arguments.of(LocalDateTime.parse("2020-01-10T00:00:00"), false),
                Arguments.of(LocalDateTime.parse("2020-01-15T00:00:00"), false)
        );
    }
}