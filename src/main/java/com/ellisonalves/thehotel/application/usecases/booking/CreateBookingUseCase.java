package com.ellisonalves.thehotel.application.usecases.booking;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.ellisonalves.thehotel.application.TimeHelper;
import com.ellisonalves.thehotel.application.vo.err.UseCaseResult;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.entity.Accommodation;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.domain.repository.AccomodationRepository;

public class CreateBookingUseCase {

	private final BookingRepository bookingRepository;

	private final AccomodationRepository accommodationRepository;

	private final GuestRepository guestRepository;

	private final TimeHelper timeHelper;

	public CreateBookingUseCase(BookingRepository bookingRepository, AccomodationRepository accommodationRepository,
			GuestRepository guestRepository, TimeHelper timeHelper) {
		this.bookingRepository = bookingRepository;
		this.accommodationRepository = accommodationRepository;
		this.timeHelper = timeHelper;
		this.guestRepository = guestRepository;
	}

	public UseCaseResult execute(CreateBookingInput input) {
		if (input.isMissingMandatoryFields()) {
			return UseCaseResult.unprocessableFailure("Missing mandatory fields");
		}

		if (input.isStartOrEndDatesBefore(timeHelper.now())) {
			return UseCaseResult.unprocessableFailure("Bookings with start and end dates in the past are not allowed");
		}

		if (input.isStartDateAfterEndDate()) {
			return UseCaseResult.unprocessableFailure("Start date MUST be before end date");
		}

		var existingBookings = bookingRepository.findBookings(input.roomId(), input.startDate(), input.endDate());

		if (existingBookings != null && !existingBookings.isEmpty()) {
			return UseCaseResult.unprocessableFailure("Booking not available");
		}

		var accomodation = accommodationRepository.findById(input.roomId());
		if (accomodation.isEmpty()) {
			return UseCaseResult.unprocessableFailure("The room does not exist");
		}

		var guest = guestRepository.findById(input.guestId());
		if (guest.isEmpty()) {
			return UseCaseResult.unprocessableFailure("The guest does not exist");
		}

		var createdBooking = persistNewBooking(input, accomodation, guest);

		return UseCaseResult.ok(createdBooking.getId().toString(), "Created!");
	}

	private Booking persistNewBooking(CreateBookingInput input, Optional<Accommodation> room, Optional<Guest> guest) {
		var newBooking = new Booking();
		newBooking.setRoom(room.get());
		newBooking.setGuest(guest.get());
		newBooking.setStartDate(input.startDate);
		newBooking.setEndDate(input.endDate());

		var createdBooking = bookingRepository.persist(newBooking);
		return createdBooking;
	}

	public static record CreateBookingInput(UUID roomId, UUID guestId, Instant startDate, Instant endDate

	) {

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

}
