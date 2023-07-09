package com.ellisonalves.thehotel.application.mappers;

import com.ellisonalves.thehotel.application.usecases.CreateBooking;
import com.ellisonalves.thehotel.domain.entity.Booking;

public interface BookingViewDomainMapper {

    Booking toDomain(CreateBooking booking);

}
