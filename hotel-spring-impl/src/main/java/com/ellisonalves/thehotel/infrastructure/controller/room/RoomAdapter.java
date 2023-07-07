package com.ellisonalves.thehotel.infrastructure.controller.room;

import java.util.UUID;

import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomCreateDto;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomUpdateDto;

import jakarta.validation.Valid;

public class RoomAdapter {

    public void createRoom(@Valid RoomCreateDto room) {
    }

    public void updateRoom(UUID id, @Valid RoomUpdateDto request) {
    }

    public RoomList findByDoorNumber(String doorNumber) {
        return null;
    }

    public RoomList findAll() {
        return null;
    }

}
