package com.ellisonalves.thehotel.infrastructure.controller.booking;

import java.time.Instant;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ellisonalves.thehotel.application.usecases.CreateBooking;
import com.ellisonalves.thehotel.application.usecases.CreateBookingUseCase;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final CreateBookingUseCase useCase;

    public BookingController(CreateBookingUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createBooking(@RequestBody CreateBookingRequest data) {
        useCase.createBooking(data);
    }

    public static class CreateBookingRequest implements CreateBooking {

        @NotNull
        private UUID guestId;

        @NotNull
        private UUID roomId;

        @NotNull
        private Instant from;

        @NotNull
        private Instant until;

        @Override
        public UUID getGuestId() {
            return guestId;
        }

        @Override
        public UUID getRoomId() {
            return roomId;
        }

        @Override
        public Instant getFrom() {
            return from;
        }

        @Override
        public Instant getUntil() {
            return until;
        }

    }

}
