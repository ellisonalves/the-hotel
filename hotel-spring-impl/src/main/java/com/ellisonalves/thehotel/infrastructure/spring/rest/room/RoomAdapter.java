package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.application.usecases.room.ManageRoomUseCase;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomData;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.spring.rest.mappers.RoomToDomainMapper;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Component
public class RoomAdapter {

    private final ManageRoomUseCase useCase;

    private final RoomToDomainMapper domainMapper;

    private final RoomToViewMapper viewMapper;

    public RoomAdapter(ManageRoomUseCase useCase,
            RoomToDomainMapper domainMapper,
            RoomToViewMapper viewMapper) {
        this.useCase = useCase;
        this.domainMapper = domainMapper;
        this.viewMapper = viewMapper;
    }

    public void createRoom(CreateRoomRequest createRoomRequest) {
        useCase.persist(domainMapper.toCreateRoomDto(createRoomRequest));
    }

    @Transactional
    public void update(UUID roomId, @Valid RoomData roomData) {
        useCase.update(roomId, viewMapper.toUpdateRoomDto(roomData));
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

}
