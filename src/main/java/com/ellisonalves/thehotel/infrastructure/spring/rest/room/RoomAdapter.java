package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.application.usecases.room.ManageRoomUseCase;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.rest.model.UpdateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.spring.rest.mappers.RoomToDomainMapstruct;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Component
public class RoomAdapter {

    private final ManageRoomUseCase useCase;

    private final RoomToDomainMapstruct domainMapper;

    private final RoomToViewMapper viewMapper;

    public RoomAdapter(ManageRoomUseCase useCase,
            RoomToDomainMapstruct domainMapper,
            RoomToViewMapper viewMapper) {
        this.useCase = useCase;
        this.domainMapper = domainMapper;
        this.viewMapper = viewMapper;
    }

    public void createRoom(CreateRoomRequest createRoomRequest) {
        useCase.persist(domainMapper.toCreateRoomDto(createRoomRequest));
    }

    @Transactional
    public void update(String doorNumber, @Valid UpdateRoomRequest roomData) {
        useCase.update(doorNumber, viewMapper.toUpdateRoomDto(roomData));
    }

    public RoomList findAll() {
        var rooms = useCase.findAll();
        return new RoomList()
                .rooms(
                        rooms.stream()
                                .map(viewMapper::toRoomData)
                                .toList());
    }

    public RoomList findByDoorNumber(String doorNumber) {
        return new RoomList().rooms(Arrays.asList(viewMapper.toRoomData(useCase.findByDoorNumber(doorNumber))));
    }

    @Transactional
    public void delete(String doorNumber) {
        useCase.deleteByDoorNumber(doorNumber);
    }

}
