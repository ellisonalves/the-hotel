package com.ellisonalves.thehotel.domain.services;

import com.ellisonalves.thehotel.domain.entities.Room;

import java.util.List;

public interface RoomService extends CRUDService<Room, Integer> {
    List<Room> findAll();
}
