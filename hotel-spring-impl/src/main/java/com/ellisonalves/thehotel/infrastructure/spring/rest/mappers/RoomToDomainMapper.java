package com.ellisonalves.thehotel.infrastructure.spring.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ellisonalves.thehotel.application.usecases.room.CreateRoomDto;
import com.ellisonalves.thehotel.application.usecases.room.RoomDomainMapper;
import com.ellisonalves.thehotel.application.usecases.room.UpdateRoomDto;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomData;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomToDomainMapper extends RoomDomainMapper {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Room toRoom(CreateRoomDto room);

    @Override
    void updateRoom(UpdateRoomDto room, @MappingTarget Room originalRoom);

    // move to some view mappers

    CreateRoomDto toCreateRoomDto(CreateRoomRequest room);

    RoomData toRoomData(Room room);

}
