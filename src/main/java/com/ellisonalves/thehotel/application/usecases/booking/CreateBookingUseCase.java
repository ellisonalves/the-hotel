package com.ellisonalves.thehotel.application.usecases.booking;

import com.ellisonalves.thehotel.application.TimeHelper;
import com.ellisonalves.thehotel.application.vo.err.UseCaseResult;
import com.ellisonalves.thehotel.domain.entity.Accommodation;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.repository.AccomodationRepository;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;

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

    public UseCaseResult execute(CreateBookingInput createBookingInput) {
        if (createBookingInput.isMissingMandatoryFields()) {
            return UseCaseResult.dataValidationError("Missing mandatory fields");
        }

        if (createBookingInput.isStartOrEndDatesBefore(timeHelper.getFirstMinuteOfToday())) {
            return UseCaseResult.dataValidationError("Bookings with start and end dates in the past are not allowed");
        }

        if (createBookingInput.isStartDateAfterEndDate()) {
            return UseCaseResult.dataValidationError("Start date MUST be before end date");
        }

        var existingBookings = bookingRepository.findBookings(createBookingInput.roomId(),
                createBookingInput.startDate(), createBookingInput.endDate());

        if (existingBookings != null && !existingBookings.isEmpty()) {
            return UseCaseResult.dataValidationError("Booking not available");
        }

        var accommodation = accommodationRepository.findById(createBookingInput.roomId());
        if (accommodation.isEmpty()) {
            return UseCaseResult.dataValidationError("The room does not exist");
        }

        var guest = guestRepository.findById(createBookingInput.guestId());
        if (guest.isEmpty()) {
            return UseCaseResult.dataValidationError("The guest does not exist");
        }

        var createdBooking = persistNewBooking(createBookingInput, accommodation.get(), guest.get());

        return UseCaseResult.ok(createdBooking.getId().toString(), "Created!");
    }

    private Booking persistNewBooking(CreateBookingInput input,
                                      Accommodation room,
                                      Guest guest) {
        var newBooking = new Booking();
        newBooking.setRoom(room);
        newBooking.setGuest(guest);
        newBooking.setStartDate(input.startDate());
        newBooking.setEndDate(input.endDate());

        return bookingRepository.persist(newBooking);
    }

}
