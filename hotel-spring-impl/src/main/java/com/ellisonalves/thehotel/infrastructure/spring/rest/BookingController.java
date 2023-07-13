package com.ellisonalves.thehotel.infrastructure.spring.rest;

import java.time.Instant;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBooking;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;

import jakarta.validation.Valid;
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
    public void createBooking(@Valid @RequestBody CreateBookingRequest data) {
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

        public void setGuestId(UUID guestId) {
            this.guestId = guestId;
        }

        @Override
        public UUID getRoomId() {
            return roomId;
        }

        public void setRoomId(UUID roomId) {
            this.roomId = roomId;
        }

        @Override
        public Instant getFrom() {
            return from;
        }

        public void setFrom(Instant from) {
            this.from = from;
        }

        @Override
        public Instant getUntil() {
            return until;
        }

        public void setUntil(Instant until) {
            this.until = until;
        }

    }

}
