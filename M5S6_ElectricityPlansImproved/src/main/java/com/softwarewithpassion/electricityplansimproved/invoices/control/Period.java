package com.softwarewithpassion.electricityplansimproved.invoices.control;

import java.time.LocalDateTime;

public class Period {
    private final LocalDateTime sinceClosed;
    private final LocalDateTime untilOpen;

    public Period(LocalDateTime sinceClosed, LocalDateTime untilOpen) {
        this.sinceClosed = sinceClosed;
        this.untilOpen = untilOpen;
    }

    public LocalDateTime getSinceClosed() {
        return sinceClosed;
    }

    public LocalDateTime getUntilOpen() {
        return untilOpen;
    }

    public boolean contains(LocalDateTime timestamp) {
        return sinceClosed.compareTo(timestamp) <= 0 && timestamp.compareTo(untilOpen) < 0;
    }
}
