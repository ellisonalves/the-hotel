package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

public record UpdateRoomDto(
        RoomType roomType,
        Money money) {

}
