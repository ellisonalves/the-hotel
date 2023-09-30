package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.aggregates.AccommodationType;

public record CreateRoomDto(
                String doorNumber,
                AccommodationType roomType,
                Money money) {
}
