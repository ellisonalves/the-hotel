package com.ellisonalves.thehotel.rest.endpoints;

import com.ellisonalves.thehotel.domain.services.RoomService;
import com.ellisonalves.thehotel.rest.interfaces.dtos.RoomDTO;
import com.ellisonalves.thehotel.rest.interfaces.dtos.RoomListDTO;
import com.ellisonalves.thehotel.rest.interfaces.mapper.RoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/rooms", produces = APPLICATION_JSON_UTF8_VALUE)
class RoomController {

    private RoomService roomService;

    private RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomService roomService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    @GetMapping(value = "{doorNumber}")
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO find(@PathVariable Integer doorNumber) {
        return roomMapper.toDTO(roomService.findOne(doorNumber).get());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomListDTO find() {
        return roomMapper.toRoomListDTO(roomService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO create(@RequestBody @Valid RoomDTO room) {
        return roomMapper.toDTO(roomService.save(roomMapper.toEntity(room)));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO update(@RequestBody RoomDTO room) {
        return roomMapper.toDTO(roomService.save(roomMapper.toEntity(room)));
    }

}
