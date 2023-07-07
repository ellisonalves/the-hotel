package com.ellisonalves.thehotel.application.usecases;

import java.time.Instant;
import java.util.UUID;

public class CreateBooking {

    private UUID guestId;
    private UUID roomId;
    private Instant from;
    private Instant until;

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public Instant getFrom() {
        return from;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getUntil() {
        return until;
    }

    public void setUntil(Instant until) {
        this.until = until;
    }

}