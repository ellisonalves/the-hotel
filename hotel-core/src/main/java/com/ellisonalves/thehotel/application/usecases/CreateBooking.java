package com.ellisonalves.thehotel.application.usecases;

import java.time.Instant;
import java.util.UUID;

public interface CreateBooking {

    public UUID getGuestId();

    public UUID getRoomId();

    public Instant getFrom();

    public Instant getUntil();

}