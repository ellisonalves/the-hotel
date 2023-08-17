package com.ellisonalves.thehotel.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Room;

public interface RoomRepository {

    Room persist(Room room);

    Optional<Room> findById(UUID id);

    void deleteByDoorNumber(String doorNumber);

    List<Room> findAll();

    Optional<Room> findByDoorNumber(String doorNumber);

}
