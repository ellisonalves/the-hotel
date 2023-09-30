package com.ellisonalves.thehotel.application;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TimeHelper {

    private final Clock clock;

    public TimeHelper(Clock clock) {
        this.clock = clock;
    }

    public Instant getFirstMinuteOfToday() {
        return getFirstMinuteOf(clock.instant());
    }

    public Instant getLastMinuteOfToday() {
        return getLastMinuteOf(clock.instant());
    }

    public Instant getFirstMinuteOf(Instant instant) {
        return instant.truncatedTo(ChronoUnit.DAYS);
    }

    public Instant getLastMinuteOf(Instant instant) {
        return instant.atZone(clock.getZone()).withHour(23).withMinute(59).withSecond(59).toInstant();
    }

}
