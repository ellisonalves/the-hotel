package com.ellisonalves.thehotel.infrastructure.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.application.mappers.BookingViewDomainMapper;
import com.ellisonalves.thehotel.application.usecases.CreateBooking;
import com.ellisonalves.thehotel.domain.entity.Booking;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingViewDomainMapperMapstruct extends BookingViewDomainMapper {

    @Override
    Booking toDomain(CreateBooking booking);

}
