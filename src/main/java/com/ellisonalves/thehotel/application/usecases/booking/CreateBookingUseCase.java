package com.ellisonalves.thehotel.application.usecases.booking;

import java.time.Clock;
import java.time.Instant;

import com.ellisonalves.thehotel.application.vo.err.Result;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

public class CreateBookingUseCase {

    private final BookingRepository repository;
    private final CreateBookingMapper mapper;

    public CreateBookingUseCase(BookingRepository repository, CreateBookingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Result createBooking(CreateBooking booking) {
        var now = Instant.now(Clock.systemUTC());

        if (booking.isMissingMandatoryFields()) {
            return Result.err("Missing mandatory fields");
        }

        if (booking.isStartOrEndDatesBefore(now)) {
            return Result.err("Bookings in the past are not allowed");
        }

        if (booking.isStartDateAfterEndDate()) {
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

    public interface CreateBookingMapper {

        Booking toDomain(CreateBooking booking);

    }
}
