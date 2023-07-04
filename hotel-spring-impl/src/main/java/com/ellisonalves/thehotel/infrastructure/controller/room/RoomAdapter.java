package com.ellisonalves.thehotel.infrastructure.controller.room;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ellisonalves.thehotel.application.usecases.ManageRoomUseCase;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomCreateDto;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomUpdateDto;

@Component
public class RoomAdapter {

    private final ManageRoomUseCase useCase;

    private final RoomModelViewMapper mapper;

    public RoomAdapter(ManageRoomUseCase manageRoomUseCase, RoomModelViewMapper mapper) {
        this.useCase = manageRoomUseCase;
        this.mapper = mapper;
    }

    @Transactional
    public void createRoom(RoomCreateDto dto) {
        useCase.save(mapper.toModel(dto));
    }

    @Transactional
    public void updateRoom(UUID id, RoomUpdateDto dto) {
        var persisted = useCase.findById(id);
        mapper.updateRoom(dto, persisted);
        useCase.save(persisted);
    }

    public RoomList findByDoorNumber(String doorNumber) {
        return mapper.toResponse(useCase.findByDoorNumber(doorNumber));
    }

    public RoomList findAll() {
        return new RoomList(mapper.toResponse(useCase.findAll()));
    }

}
