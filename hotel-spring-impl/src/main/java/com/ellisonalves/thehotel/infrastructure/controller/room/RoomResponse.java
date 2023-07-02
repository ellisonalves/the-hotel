package com.ellisonalves.thehotel.infrastructure.controller.room;

import java.util.List;

public class RoomResponse {
    
    private List<RoomRequest> rooms;

    public RoomResponse(List<RoomRequest> rooms) {
        this.rooms = rooms;
    }

    public List<RoomRequest> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomRequest> rooms) {
        this.rooms = rooms;
    }

}
