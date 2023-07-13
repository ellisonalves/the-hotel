package com.ellisonalves.thehotel.application.usecases.booking;

import com.ellisonalves.thehotel.domain.entity.Booking;

public interface CreateBookingMapper {

    Booking toDomain(CreateBooking booking);

}
