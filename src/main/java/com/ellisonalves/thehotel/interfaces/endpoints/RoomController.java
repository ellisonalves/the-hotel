package com.ellisonalves.thehotel.interfaces.endpoints;


import com.ellisonalves.thehotel.domain.services.RoomService;
import com.ellisonalves.thehotel.interfaces.dtos.RoomDTO;
import com.ellisonalves.thehotel.interfaces.dtos.RoomListDTO;
import com.ellisonalves.thehotel.interfaces.mapper.RoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/rooms")
class RoomController {

    private RoomService roomService;

    private RoomMapper roomMapper;

    @Autowired
    public RoomController(RoomService roomService, RoomMapper roomMapper) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
    }

    @GetMapping(value = "{doorNumber}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO find(@PathVariable Integer doorNumber) {
        return roomMapper.toDTO(roomService.findOne(doorNumber));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public RoomListDTO find() {
        return roomMapper.toRoomListDTO(roomService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDTO create(@RequestBody @Valid RoomDTO room) {
        return roomMapper.toDTO(roomService.save(roomMapper.toEntity(room)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO update(@RequestBody RoomDTO room) {
        return roomMapper.toDTO(roomService.save(roomMapper.toEntity(room)));
    }

}
