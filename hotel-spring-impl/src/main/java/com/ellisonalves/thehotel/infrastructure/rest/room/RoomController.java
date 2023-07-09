package com.ellisonalves.thehotel.infrastructure.rest.room;

import java.util.UUID;

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

import com.ellisonalves.thehotel.infrastructure.rest.room.model.RoomCreateDto;
import com.ellisonalves.thehotel.infrastructure.rest.room.model.RoomList;
import com.ellisonalves.thehotel.infrastructure.rest.room.model.RoomUpdateDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
class RoomController {

    private final RoomAdapter adapter;

    public RoomController(final RoomAdapter roomAdapter) {
        this.adapter = roomAdapter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid RoomCreateDto room) {
        adapter.createRoom(room);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@PathVariable UUID id, @RequestBody @Valid RoomUpdateDto request) {
        adapter.updateRoom(id, request);
    }

    @GetMapping("/{doorNumber}")
    @ResponseStatus(HttpStatus.OK)
    public RoomList findByDoorNumber(@PathVariable String doorNumber) {
        return adapter.findByDoorNumber(doorNumber);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomList findAll() {
        return adapter.findAll();
    }

}
