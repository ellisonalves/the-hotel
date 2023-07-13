package com.ellisonalves.thehotel.infrastructure.spring.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBooking;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingMapper;
import com.ellisonalves.thehotel.domain.entity.Booking;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateBookingMapstruct extends CreateBookingMapper {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", source = "from")
    @Mapping(target = "endDate", source = "until")
    @Mapping(target = "version", ignore = true)
    Booking toDomain(CreateBooking booking);

}
