package com.ellisonalves.thehotel.application.usecases.booking;

import java.time.Clock;
import java.time.Instant;

import com.ellisonalves.thehotel.application.vo.err.Result;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

public class CreateBookingUseCase {

	private final BookingRepository repository;

	public CreateBookingUseCase(BookingRepository repository) {
		this.repository = repository;
	}

	public Result createBooking(Booking booking) {
		var now = Instant.now(Clock.systemUTC());

		if (booking.isMissingMandatoryFields()) {
			return Result.unprocessableFailure("Missing mandatory fields");
		}

		if (booking.isStartOrEndDatesBefore(now)) {
			return Result.unprocessableFailure("Bookings in the past are not allowed");
		}

		if (booking.isStartDateAfterEndDate()) {
			return Result.unprocessableFailure("Start date MUST be before end date");
		}

		var existingBookings = repository.findBookings(booking.getRoom().getId(), booking.getStartDate(),
				booking.getEndDate());

		if (existingBookings != null && !existingBookings.isEmpty()) {
			return Result.unprocessableFailure("Booking not available");
		}

		var createdBooking = repository.persist(booking);

		return Result.ok(createdBooking.getId().toString(), "Created!");
	}

}
