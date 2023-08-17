package com.ellisonalves.thehotel.application.usecases.room;

import java.util.List;
import java.util.UUID;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

public class ManageRoomUseCase {

    private final RoomRepository repository;
    private final RoomDomainMapper mapper;

    public ManageRoomUseCase(RoomRepository repository, RoomDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void persist(CreateRoomDto room) {
        repository.persist(mapper.toRoom(room));
    }

    public void update(String doorNumber, UpdateRoomDto updateRoomDto) {
        var originalRoom = findByDoorNumber(doorNumber);

        mapper.updateRoom(updateRoomDto, originalRoom);

        repository.persist(originalRoom);
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

    public void deleteByDoorNumber(String doorNumber) {
        repository.deleteByDoorNumber(doorNumber);
    }
}
