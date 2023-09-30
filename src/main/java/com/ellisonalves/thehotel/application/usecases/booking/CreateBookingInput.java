package com.ellisonalves.thehotel.application.usecases.booking;

import java.time.Instant;
import java.util.UUID;

public record CreateBookingInput(UUID roomId, UUID guestId, Instant startDate, Instant endDate) {

    public boolean isMissingMandatoryFields() {
        return !(hasGuestId() || hasRoomId() || hasStartDate() || hasEndDate());
    }

    public boolean isStartOrEndDatesBefore(Instant instant) {
        return hasStartAndEndDates() && (startDate.isBefore(instant) || endDate.isBefore(instant));
    }

    public boolean isStartDateAfterEndDate() {
        return hasStartAndEndDates() && startDate.isAfter(endDate);
    }

    private boolean hasEndDate() {
        return endDate != null;
    }

    private boolean hasStartDate() {
        return startDate != null;
    }

    private boolean hasRoomId() {
        return roomId != null;
    }

    private boolean hasGuestId() {
        return guestId != null;
    }

    private boolean hasStartAndEndDates() {
        return hasStartDate() && hasEndDate();
    }
}

