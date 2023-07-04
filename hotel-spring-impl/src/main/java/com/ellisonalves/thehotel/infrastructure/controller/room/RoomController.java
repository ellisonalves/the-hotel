package com.ellisonalves.thehotel.infrastructure.controller.room;

import java.util.UUID;

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
import com.ellisonalves.thehotel.infrastructure.controller.room.mappers.RoomModelViewMapper;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomCreateDto;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.controller.room.model.RoomUpdateDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
class RoomController {

    private final ManageRoomUseCase manageRoomUseCase;

    private final RoomModelViewMapper mapper;

    @Autowired
    public RoomController(final ManageRoomUseCase saveRoomUseCase, RoomModelViewMapper mapper) {
        this.manageRoomUseCase = saveRoomUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid RoomCreateDto room) {
        manageRoomUseCase.save(mapper.toModel(room));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@PathVariable("id") UUID id, @RequestBody @Valid RoomUpdateDto request) {
        var persisted = manageRoomUseCase.findById(id);
        mapper.updateRoom(request, persisted);
        manageRoomUseCase.save(persisted);
    }

    @GetMapping("/{doorNumber}")
    @ResponseStatus(HttpStatus.OK)
    public RoomList findByDoorNumber(@PathVariable String doorNumber) {
        var room = manageRoomUseCase.findByDoorNumber(doorNumber);
        return mapper.toResponse(room);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomList findAll() {
        return new RoomList(mapper.toResponse(manageRoomUseCase.findAll()));
    }

}
