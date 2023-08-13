package com.ellisonalves.thehotel.infrastructure.spring.jpa.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.RoomJpa;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomJpaModelMapper {

    RoomJpa toEntity(Room model);

    Room toModel(RoomJpa room);

}
