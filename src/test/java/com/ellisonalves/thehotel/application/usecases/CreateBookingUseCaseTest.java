package com.ellisonalves.thehotel.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBooking;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingMapper;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.vo.err.Result;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

@ExtendWith(MockitoExtension.class)
public class CreateBookingUseCaseTest {

    @InjectMocks
    private CreateBookingUseCase useCase;

    @Mock
    private BookingRepository repository;

    @Mock
    private CreateBookingMapper mapper;

    @Test
    void shouldCreateBookingSuccessfully() {
        final var newBooking = createBookingRequest();

        var booking = new Booking();

        when(repository.findBookings(newBooking.roomId(), newBooking.from(),
                newBooking.until()))
                .thenReturn(Collections.emptyList());

        when(mapper.toDomain(newBooking)).thenReturn(booking);

        var actual = useCase.createBooking(newBooking);

        assertEquals(Result.ok("Created!"), actual);

        verify(repository, atMostOnce()).persist(any(Booking.class));
    }

    @Test
    void shouldNotBookWhenTheRoomIsBusy() {
        var newBooking = createBookingRequest();

        var booking = new Booking();

        when(repository.findBookings(newBooking.roomId(), newBooking.from(),
                newBooking.until()))
                .thenReturn(List.of(booking));

        var actual = useCase.createBooking(newBooking);

        assertEquals(Result.err("Booking not available"), actual);

        verify(repository, never()).persist(booking);
    }

    @Test
    void shouldNotAllowBookingsInThePast() {
        var yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        var lastWeek = yesterday.minus(5, ChronoUnit.DAYS);

        var actual = useCase
                .createBooking(new CreateBooking(UUID.randomUUID(), UUID.randomUUID(), lastWeek, yesterday));

        assertEquals(Result.err("Bookings in the past are not allowed"), actual);

        verify(repository, never()).persist(any());
    }

    @Test
    void shouldNotAllowStartDateBeforeEndDate() {
        var today = Instant.now(Clock.systemUTC()).plus(1, ChronoUnit.SECONDS); // TODO extract and mock clock
        var nextWeek = today.plus(5, ChronoUnit.DAYS);

        var actual = useCase
                .createBooking(new CreateBooking(UUID.randomUUID(), UUID.randomUUID(), nextWeek, today));

        assertEquals(Result.err("Start date MUST be before end date"), actual);

        verify(repository, never()).persist(any());
    }

    @Test
    public void shouldNotAllowMissingWhenThereAreMissingProperties() {
        var actual = useCase.createBooking(new CreateBooking(null, null, null, null));

        assertEquals(Result.err("Missing mandatory fields"), actual);

        verify(repository, never()).persist(any());
    }

    private CreateBooking createBookingRequest() {
        return new CreateBooking(
                UUID.randomUUID(),
                UUID.randomUUID(),
                Instant.now().plus(1, ChronoUnit.DAYS),
                Instant.now().plus(3, ChronoUnit.DAYS));
    }
}
