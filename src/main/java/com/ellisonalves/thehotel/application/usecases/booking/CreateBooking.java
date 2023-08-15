package com.ellisonalves.thehotel.application.usecases.booking;

import java.time.Instant;
import java.util.UUID;

public record CreateBooking(

        UUID guestId,

        UUID roomId,

        Instant from,

        Instant until) {

    public boolean isMissingMandatoryFields() {
        return guestId == null || roomId == null || from == null || until == null;
    }

    public boolean isStartOrEndDatesBefore(Instant instant) {
        return (from != null && until != null) && from.isBefore(instant) || until.isBefore(instant);
    }

    public boolean isStartDateAfterEndDate() {
        return from.isAfter(until);
    }

}