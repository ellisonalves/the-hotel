package com.ellisonalves.thehotel.infrastructure.mappers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.controller.room.RoomRequest;
import com.ellisonalves.thehotel.infrastructure.controller.room.RoomResponse;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.RoomJpa;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {

    RoomJpa toEntity(Room dto);

    Room toDto(RoomJpa room);

    default List<RoomJpa> toEntityList(Collection<Room> entities) {
        return entities.stream()
                .map(dto -> toEntity(dto))
                .collect(Collectors.toList());
    }

    default List<Room> toDtoList(Collection<RoomJpa> entities) {
        return entities.stream()
                .map(dto -> toEntity(dto))
                .collect(Collectors.toList());
    }

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
