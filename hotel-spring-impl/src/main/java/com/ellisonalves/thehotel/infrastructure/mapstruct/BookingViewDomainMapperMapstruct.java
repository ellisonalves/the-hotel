package com.ellisonalves.thehotel.infrastructure.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingMapper;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBooking;
import com.ellisonalves.thehotel.domain.entity.Booking;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingViewDomainMapperMapstruct extends CreateBookingMapper {

    @Override
    Booking toDomain(CreateBooking booking);

}
