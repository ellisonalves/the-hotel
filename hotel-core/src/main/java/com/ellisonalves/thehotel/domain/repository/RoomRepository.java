package com.ellisonalves.thehotel.domain.repository;

import java.util.List;
import java.util.Optional;

import com.ellisonalves.thehotel.domain.entity.Room;

public interface RoomRepository<T extends Room, ID> {

    void persist(T room);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    List<T> findAll();

    Optional<T> findByDoorNumber(String doorNumber);

}
