package com.ellisonalves.thehotel.infrastructure.controller.room.mappers;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomCreateDto;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomUpdateDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomModelViewMapper {

    Room toModel(RoomCreateDto view);

    RoomCreateDto toView(Room model);

    void updateRoom(RoomUpdateDto view, @MappingTarget Room model);

    default RoomList toResponse(Room room) {
        return new RoomList(Arrays.asList(toView(room)));
    }

    default List<RoomCreateDto> toResponse(Collection<Room> entities) {
        return entities.stream()
                .map(entity -> toView(entity))
                .collect(Collectors.toList());
    }

}
