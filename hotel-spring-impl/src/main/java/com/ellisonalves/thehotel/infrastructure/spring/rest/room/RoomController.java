package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ellisonalves.thehotel.infrastructure.rest.RoomsApi;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateRoomRequest;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomData;
import com.ellisonalves.thehotel.infrastructure.rest.model.RoomList;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RoomController implements RoomsApi {

    private final RoomAdapter adapter;

    public RoomController(RoomAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public ResponseEntity<Void> createRoom(CreateRoomRequest createRoomRequest) {
        adapter.createRoom(createRoomRequest);

        return ResponseEntity.created(
                UriComponentsBuilder.fromUriString("rooms")
                        .build("test"))
                .build();
    }

    @Override
    public ResponseEntity<com.ellisonalves.thehotel.infrastructure.rest.model.RoomList> findAll() {
        return ResponseEntity.ok().body(adapter.findAll());
    }

    @Override
    public ResponseEntity<Void> updateRoom(String doorNumber, @Valid RoomData roomData) {
        adapter.update(doorNumber, roomData);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RoomList> findByDoorNumber(String doorNumber) {
        return ResponseEntity.ok().body(adapter.findByDoorNumber(doorNumber));
    }

}
