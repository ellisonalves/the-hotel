package com.ellisonalves.thehotel.infrastructure.spring.rest.room.model;

import java.util.List;

public class RoomList {
    
    private List<RoomCreateDto> rooms;

    public RoomList(List<RoomCreateDto> rooms) {
        this.rooms = rooms;
    }

    public List<RoomCreateDto> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomCreateDto> rooms) {
        this.rooms = rooms;
    }

}
