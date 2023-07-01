package com.ellisonalves.thehotel.infrastructure.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.RoomJpa;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {

    // RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomJpa toEntity(RoomRequest dto);

    RoomRequest toDto(Room room);

    void updateRoom(RoomRequest request, @MappingTarget Room roomJpa);

    default RoomResponse toResponse(Room room) {
        return new RoomResponse(Arrays.asList(toDto(room)));
    }

    default List<RoomRequest> toResponse(Collection<Room> entities) {
        return entities.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

}
