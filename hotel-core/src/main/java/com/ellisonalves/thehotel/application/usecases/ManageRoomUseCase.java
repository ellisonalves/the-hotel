package com.ellisonalves.thehotel.application.usecases;

import java.util.List;
import java.util.UUID;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

public class ManageRoomUseCase {

    private final RoomRepository<Room, UUID> repository;

    public ManageRoomUseCase(RoomRepository<Room, UUID> repository) {
        this.repository = repository;
    }

    public void save(Room room) {
        repository.persist(room);
    }

    public Room findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    public Room findByDoorNumber(String doorNumber) {
        return repository.findByDoorNumber(doorNumber).orElseThrow(() -> new ResourceNotFoundException());
    }

    public List<Room> findAll() {
        return repository.findAll();
    }

}