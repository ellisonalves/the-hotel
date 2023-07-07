package com.ellisonalves.thehotel.infrastructure.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.infrastructure.jpa.repository.BookingJpa;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingJpaModelMapper {

    public BookingJpa toJpa(Booking booking);

    public Booking toDomain(BookingJpa p);

}
