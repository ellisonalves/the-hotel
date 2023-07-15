package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.application.usecases.room.UpdateRoomDto;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomData;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomToViewMapper {

    UpdateRoomDto toUpdateRoomDto(RoomData roomData);

    RoomData toRoomData(Room room);

}
