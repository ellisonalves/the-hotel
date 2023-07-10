package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.infrastructure.spring.rest.room.model.RoomCreateDto;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.model.RoomUpdateDto;

import jakarta.validation.Valid;

@Component
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
