package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

public record CreateRoomDto(
                String doorNumber,
                RoomType roomType,
                Money money) {
}
