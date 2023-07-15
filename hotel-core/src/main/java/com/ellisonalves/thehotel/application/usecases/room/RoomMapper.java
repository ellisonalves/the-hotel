package com.ellisonalves.thehotel.application.usecases.room;

import com.ellisonalves.thehotel.domain.entity.Room;

public interface RoomMapper {

    Room toDomain(CreateRoomDto room);

    void update(UpdateRoomDto room, Room originalRoom);

}
