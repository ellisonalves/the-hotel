package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.aggregates.AccommodationType;

public record UpdateRoomDto(
        AccommodationType roomType,
        Money money) {

}
