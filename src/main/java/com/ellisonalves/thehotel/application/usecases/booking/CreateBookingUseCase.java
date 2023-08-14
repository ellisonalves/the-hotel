package com.ellisonalves.thehotel.application.usecases.booking;

import java.time.Clock;
import java.time.Instant;

import com.ellisonalves.thehotel.application.vo.err.Result;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

public class CreateBookingUseCase {

    private final BookingRepository repository;
    private final CreateBookingMapper mapper;

    public CreateBookingUseCase(BookingRepository repository, CreateBookingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Result createBooking(CreateBooking booking) {
        var now = Instant.now(Clock.systemUTC()); // TODO refactor to be reused

        if (booking.guestId() == null || booking.roomId() == null || booking.from() == null
                || booking.until() == null) { // TODO extract to the vO
            return Result.err("Missing mandatory fields");
        }

        if (now.isAfter(booking.from()) || now.isAfter(booking.until())) { // TODO extract to the VO
            return Result.err("Bookings in the past are not allowed");
        }

        if (booking.from().isAfter(booking.until())) { // extract to the VO
            return Result.err("Start date MUST be before end date");
        }

        var existingBookings = repository.findBookings(
                booking.roomId(),
                booking.from(),
                booking.until());

        if (existingBookings != null && !existingBookings.isEmpty()) {
            return Result.err("Booking not available");
        }

        repository.persist(mapper.toDomain(booking));

        return Result.ok("Created!");
    }
}
