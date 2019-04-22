package com.ellisonalves.thehotel.interfaces.endpoints;


import com.ellisonalves.thehotel.interfaces.dtos.RoomDTO;
import com.ellisonalves.thehotel.interfaces.dtos.RoomListDTO;
import com.ellisonalves.thehotel.interfaces.mapper.RoomMapper;
import com.ellisonalves.thehotel.usecases.FindRoomsUseCase;
import com.ellisonalves.thehotel.usecases.PersistRoomsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private FindRoomsUseCase findRoomsUseCase;

    private PersistRoomsUseCase persitRoomsUseCase;

    private RoomMapper roomMapper;

    @Autowired
    public RoomController(FindRoomsUseCase findRoomsUseCase, PersistRoomsUseCase persitRoomsUseCase, RoomMapper roomMapper) {
        this.findRoomsUseCase = findRoomsUseCase;
        this.persitRoomsUseCase = persitRoomsUseCase;
        this.roomMapper = roomMapper;
    }

    @GetMapping(value = "{doorNumber}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomDTO> find(@PathVariable Integer doorNumber) {
        return ResponseEntity.ok(roomMapper.toDTO(findRoomsUseCase.findOne(doorNumber)));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomListDTO> find() {
        return ResponseEntity.ok(roomMapper.toRoomListDTO(findRoomsUseCase.findAll()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO) {
        RoomDTO saved = roomMapper.toDTO(persitRoomsUseCase.save(roomMapper.toEntity(roomDTO)));
        return ResponseEntity.created(URI.create("/rooms/" + saved.getDoorNumber())).body(saved);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomDTO> update(@RequestBody RoomDTO roomDTO) {
        RoomDTO updated = roomMapper.toDTO(persitRoomsUseCase.save(roomMapper.toEntity(roomDTO)));
        return ResponseEntity.ok(updated);
    }

}
