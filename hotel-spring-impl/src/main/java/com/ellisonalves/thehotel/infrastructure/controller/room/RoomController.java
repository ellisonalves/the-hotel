package com.ellisonalves.thehotel.infrastructure.controller.room;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ellisonalves.thehotel.application.usecases.ManageRoomUseCase;
import com.ellisonalves.thehotel.infrastructure.mappers.RoomMapper;

@RestController
@RequestMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
class RoomController {

    private final ManageRoomUseCase manageRoomUseCase;

    private final RoomMapper mapper;

    @Autowired
    public RoomController(final ManageRoomUseCase saveRoomUseCase, RoomMapper mapper) {
        this.manageRoomUseCase = saveRoomUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid RoomRequest room) {
        manageRoomUseCase.save(mapper.toEntity(room));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@PathVariable("id") UUID id, @RequestBody @Valid RoomRequest request) {
        var persisted = manageRoomUseCase.findById(id);
        mapper.updateRoom(request, persisted);
        manageRoomUseCase.save(persisted);
    }

    @GetMapping("/{doorNumber}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponse findByDoorNumber(@PathVariable String doorNumber) {
        var room = manageRoomUseCase.findByDoorNumber(doorNumber);
        return mapper.toResponse(room);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomResponse findAll() {
        return new RoomResponse(mapper.toResponse(manageRoomUseCase.findAll()));
    }

}
