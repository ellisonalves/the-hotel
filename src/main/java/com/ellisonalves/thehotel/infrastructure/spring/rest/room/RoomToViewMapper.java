package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.ellisonalves.thehotel.application.usecases.room.UpdateRoomDto;
import com.ellisonalves.thehotel.domain.entity.Accommodation;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomData;
import com.ellisonalves.thehotel.infrastructure.rest.model.UpdateRoomRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomToViewMapper {

    @Mapping(target = "money.amount", source = "unitPrice.amount")
    @Mapping(target = "money.currency", source = "unitPrice.currencyCode")
    UpdateRoomDto toUpdateRoomDto(UpdateRoomRequest roomData);

    @Mapping(target = "unitPrice.amount", source = "amount")
    @Mapping(target = "unitPrice.currencyCode", source = "currency")
    RoomData toRoomData(Accommodation room);

}
