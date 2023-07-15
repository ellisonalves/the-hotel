package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

public record UpdateRoomDto(
        String doorNumber,
        RoomType roomType,
        Money amount) {

}
