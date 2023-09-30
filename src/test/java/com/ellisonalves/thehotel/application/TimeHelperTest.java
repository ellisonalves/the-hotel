package com.ellisonalves.thehotel.application;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class TimeHelperTest {

    private static final Instant now = Instant.parse("2023-08-19T19:14:26Z");

    private static final Clock clock = Clock.fixed(now, ZoneOffset.UTC);

    private final TimeHelper helper = new TimeHelper(clock);

    @Test
    void shouldReturnTheFirstInstantOfToday() {
        Instant expected = Instant.parse("2023-08-19T00:00:00Z");
        assertThat(helper.getFirstMinuteOfToday()).isEqualTo(expected);
    }

    @Test
    void shouldReturnTheLastInstantOfToday() throws Exception {
        Instant expected = Instant.parse("2023-08-19T23:59:59Z");
        assertThat(helper.getLastMinuteOfToday()).isEqualTo(expected);
    }

}
