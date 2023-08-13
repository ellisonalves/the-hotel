package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ellisonalves.thehotel.annotations.DatabaseTest;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

@DatabaseTest
public class BookingJpaRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    private final UUID roomId = UUID.randomUUID();

    private final Instant start = Instant.parse("2023-07-07T14:00:00.00Z");
    private final Instant end = Instant.parse("2023-07-20T11:00:00.00Z");

    @Test
    void testFindBookingsPerRoomAndDateRange() {

    }

    @Test
    void testPersist() {

    }

    @Test
    void shouldNotFindAnyBookingForTheProvidedCriteria() {
        var bookings = bookingRepository.findBookings(roomId, start, end);
        assertThat(bookings).isEmpty();
    }
}
