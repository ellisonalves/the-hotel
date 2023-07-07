package com.ellisonalves.thehotel.application.usecases;

import com.ellisonalves.thehotel.domain.entity.Booking;

public interface BookingViewDomainMapper {

    Booking toDomain(CreateBooking booking);

}
