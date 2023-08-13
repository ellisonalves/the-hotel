package com.ellisonalves.thehotel.infrastructure.spring.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.GuestJpa;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GuestJpaModelMapper {

    Guest toDomain(GuestJpa guest);

    GuestJpa toJpa(Guest guest);

}
