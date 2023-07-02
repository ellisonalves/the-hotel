package com.ellisonalves.thehotel.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.GuestJpa;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GuestMapper {
    GuestJpa toEntity(Guest dto);
}