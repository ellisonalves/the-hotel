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

import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

@ExtendWith(MockitoExtension.class)
public class CreateBookingUseCaseTest {

    @InjectMocks
    private CreateBookingUseCase useCase;

    @Mock
    private BookingRepository repository;

    @Mock
    private BookingViewDomainMapper mapper;

    @Test
    void shouldCreateBookingSuccessfully() {
        var newBooking = new CreateBooking();
        newBooking.setRoomId(UUID.randomUUID());
        newBooking.setFrom(Instant.parse("2023-07-07T14:00:00.00z"));
        newBooking.setUntil(Instant.parse("2023-07-20T11:00:00.00z"));

        var booking = new Booking();

        when(repository.findBookingsPerRoomAndDateRange(newBooking.getRoomId(), newBooking.getFrom(),
                newBooking.getUntil()))
                .thenReturn(Collections.emptyList());

        when(mapper.toDomain(newBooking)).thenReturn(booking);

        useCase.createBooking(newBooking);

        verify(repository, atMostOnce()).persist(any(Booking.class));
    }

    @Test
    void shouldNotBookWhenTheRoomIsBusy() {
        var newBooking = new CreateBooking();

        var booking = new Booking();

        when(repository.findBookingsPerRoomAndDateRange(newBooking.getRoomId(), newBooking.getFrom(),
                newBooking.getUntil()))
                .thenReturn(List.of(booking));

        useCase.createBooking(newBooking);

        verify(repository, never()).persist(booking);
    }
}
