package com.ellisonalves.thehotel.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Room;

public interface RoomRepository {

    void persist(Room room);

    Optional<Room> findById(UUID id);

    void deleteById(UUID id);

    List<Room> findAll();

    Optional<Room> findByDoorNumber(String doorNumber);

}
