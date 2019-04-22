package com.ellisonalves.thehotel.usecases.impl;

import com.ellisonalves.thehotel.domain.entities.Room;
import com.ellisonalves.thehotel.domain.repositories.RoomRepository;
import com.ellisonalves.thehotel.usecases.PersistRoomsUseCase;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
class PersistRoomsUseCaseImpl implements PersistRoomsUseCase {
    private RoomRepository roomRepository;

    public PersistRoomsUseCaseImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Room save(Room room) {
        return roomRepository.save(room);
    }
}
