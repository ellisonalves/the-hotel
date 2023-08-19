package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.entity.Accommodation;

public interface RoomDomainMapper {

    Accommodation toRoom(CreateRoomDto room);

    void updateRoom(UpdateRoomDto room, Accommodation originalRoom);

}
