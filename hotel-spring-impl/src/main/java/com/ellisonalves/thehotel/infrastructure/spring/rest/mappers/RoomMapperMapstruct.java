package com.ellisonalves.thehotel.infrastructure.spring.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.application.usecases.room.CreateRoom;
import com.ellisonalves.thehotel.application.usecases.room.RoomMapper;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomData;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapperMapstruct extends RoomMapper {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Room toDomain(CreateRoom room);

    CreateRoom toDomain(CreateRoomRequest room);

    RoomData toView(Room room);

}
