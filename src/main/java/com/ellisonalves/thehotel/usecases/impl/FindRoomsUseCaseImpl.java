package com.ellisonalves.thehotel.usecases.impl;

import com.ellisonalves.thehotel.domain.entities.Room;
import com.ellisonalves.thehotel.domain.repositories.RoomRepository;
import com.ellisonalves.thehotel.usecases.FindRoomsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindRoomsUseCaseImpl implements FindRoomsUseCase {
    private RoomRepository roomRepository;

    public FindRoomsUseCaseImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findOne(Integer doorNumber) {
        return roomRepository.findById(doorNumber).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
