package com.ellisonalves.thehotel.application.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
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

        useCase.createBooking(newBooking);

        verify(repository, atMostOnce()).persist(any(Booking.class));
    }

    @Test
    void shouldNotBookWhenTheRoomIsBusy() {
        var newBooking = createBookingRequest();

        var booking = new Booking();

        when(repository.findBookings(newBooking.roomId(), newBooking.from(),
                newBooking.until()))
                .thenReturn(List.of(booking));

        useCase.createBooking(newBooking);

        verify(repository, never()).persist(booking);
    }

    private CreateBooking createBookingRequest() {
        return new CreateBooking(
                UUID.randomUUID(),
                UUID.randomUUID(),
                Instant.parse("2023-07-07T14:00:00.00z"),
                Instant.parse("2023-07-20T11:00:00.00z"));
    }
}
