package com.ellisonalves.thehotel.application.usecases.booking;

import java.time.Instant;
import java.util.UUID;

public record CreateBooking(

                UUID guestId,

                UUID roomId,

                Instant from,

                Instant until) {

}