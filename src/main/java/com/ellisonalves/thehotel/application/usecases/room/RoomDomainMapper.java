package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.entity.Room;

public interface RoomDomainMapper {

    Room toRoom(CreateRoomDto room);

    void updateRoom(UpdateRoomDto room, Room originalRoom);

}
