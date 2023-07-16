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
public interface RoomToDomainMapstruct extends RoomDomainMapper {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "amount", source = "money.amount")
    @Mapping(target = "currency", source = "money.currency")
    Room toRoom(CreateRoomDto room);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "doorNumber", ignore = true)
    @Mapping(target = "roomType", source = "roomType")
    @Mapping(target = "currency", source = "money.currency")
    @Mapping(target = "amount", source = "money.amount")
    void updateRoom(UpdateRoomDto room, @MappingTarget Room originalRoom);

    @Mapping(target = "money.amount", source = "unitPrice.amount")
    @Mapping(target = "money.currency", source = "unitPrice.currencyCode")
    CreateRoomDto toCreateRoomDto(CreateRoomRequest room);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "doorNumber", source = "doorNumber")
    @Mapping(target = "roomType", source = "roomType")
    @Mapping(target = "unitPrice.amount", source = "amount")
    @Mapping(target = "unitPrice.currencyCode", source = "currency")
    RoomData toRoomData(Room room);

}
