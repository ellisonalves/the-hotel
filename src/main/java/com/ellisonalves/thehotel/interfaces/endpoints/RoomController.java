package com.ellisonalves.thehotel.interfaces.endpoints;


import com.ellisonalves.thehotel.domain.services.RoomService;
import com.ellisonalves.thehotel.interfaces.dtos.RoomDTO;
import com.ellisonalves.thehotel.interfaces.dtos.RoomListDTO;
import com.ellisonalves.thehotel.interfaces.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<RoomDTO> find(@PathVariable Integer doorNumber) {
        return ResponseEntity.ok(roomMapper.toDTO(roomService.findOne(doorNumber)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomListDTO> find() {
        return ResponseEntity.ok(roomMapper.toRoomListDTO(roomService.findAll()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO) {
        RoomDTO saved = roomMapper.toDTO(roomService.save(roomMapper.toEntity(roomDTO)));
        return ResponseEntity.created(URI.create("/rooms/" + saved.getDoorNumber())).body(saved);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomDTO> update(@RequestBody RoomDTO roomDTO) {
        RoomDTO updated = roomMapper.toDTO(roomService.save(roomMapper.toEntity(roomDTO)));
        return ResponseEntity.ok(updated);
    }

}
