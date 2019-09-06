package com.ellisonalves.thehotel.domain.services.impl;


import com.ellisonalves.thehotel.domain.entities.Room;
import com.ellisonalves.thehotel.domain.repositories.RoomRepository;
import com.ellisonalves.thehotel.domain.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> findOne(Integer doorNumber) {
        return roomRepository.findById(doorNumber);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }
}
