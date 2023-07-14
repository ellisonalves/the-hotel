package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ellisonalves.thehotel.application.usecases.room.ManageRoomUseCase;
import com.ellisonalves.thehotel.infrastructure.rest.RoomsApi;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.spring.rest.mappers.RoomMapperMapstruct;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.model.RoomUpdateDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RoomController implements RoomsApi {

    private final ManageRoomUseCase useCase;

    private final RoomMapperMapstruct mapper;

    public RoomController(ManageRoomUseCase useCase, RoomMapperMapstruct mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Void> createRoom(CreateRoomRequest createRoomRequest) {
        useCase.save(mapper.toDomain(createRoomRequest));

        return ResponseEntity.created(
                UriComponentsBuilder.fromUriString("rooms")
                        .build("test"))
                .build();
    }

    @Override
    public ResponseEntity<com.ellisonalves.thehotel.infrastructure.rest.model.RoomList> findAll() {
        var rooms = useCase.findAll();
        return ResponseEntity.ok().body(new RoomList().rooms(rooms.stream().map(mapper::toView).toList()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@PathVariable UUID id, @RequestBody @Valid RoomUpdateDto request) {
        // adapter.updateRoom(id, request);
    }

    // @GetMapping("/{doorNumber}")
    // @ResponseStatus(HttpStatus.OK)
    // public RoomList findByDoorNumber(@PathVariable String doorNumber) {
    // // return adapter.findByDoorNumber(doorNumber);
    // return new RoomList(Collections.emptyList());
    // }

    // @GetMapping
    // @ResponseStatus(HttpStatus.OK)
    // public RoomList findAll() {
    // // return adapter.findAll();
    // return new RoomList(Collections.emptyList());
    // }

}
