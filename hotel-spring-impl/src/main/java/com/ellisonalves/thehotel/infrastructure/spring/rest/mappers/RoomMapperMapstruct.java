package com.ellisonalves.thehotel.infrastructure.spring.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.ellisonalves.thehotel.application.usecases.room.CreateRoomDto;
import com.ellisonalves.thehotel.application.usecases.room.RoomMapper;
import com.ellisonalves.thehotel.application.usecases.room.UpdateRoomDto;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomData;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapperMapstruct extends RoomMapper {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Room toDomain(CreateRoomDto room);

    CreateRoomDto toDomain(CreateRoomRequest room);

    RoomData toView(Room room);

    @Override
    void update(UpdateRoomDto room, @MappingTarget Room originalRoom);

    UpdateRoomDto toDomain(RoomData roomData);

}
