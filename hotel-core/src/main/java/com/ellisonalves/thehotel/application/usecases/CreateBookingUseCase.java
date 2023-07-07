package com.ellisonalves.thehotel.application.usecases;

import com.ellisonalves.thehotel.domain.repository.BookingRepository;

public class CreateBookingUseCase {

    private final BookingRepository repository;
    private final BookingViewDomainMapper mapper;

    public CreateBookingUseCase(BookingRepository repository, BookingViewDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void createBooking(CreateBooking booking) {
        var existingBookings = repository.findBookingsPerRoomAndDateRange(booking.getRoomId(), booking.getFrom(),
                booking.getUntil());

        if (existingBookings != null && !existingBookings.isEmpty()) {
            return;
        }

        repository.persist(mapper.toDomain(booking));
    }
}
