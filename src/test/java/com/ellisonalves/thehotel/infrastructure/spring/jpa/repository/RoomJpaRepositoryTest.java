package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.spring.annotations.DatabaseTest;

@DatabaseTest
class RoomJpaRepositoryTest {

    @Autowired
    private RoomJpaRepository repository;

    @Test
    void shouldPersistRoom() {
        repository.persist(createRoom());
    }

    @Test
    void shouldReturnNoRoom() {
        assertThat(repository.findById(UUID.randomUUID())).isNotPresent();
    }

    @Test
    void shouldFindRoomById() {
        var newRoom = createRoom();
        var persisted = repository.persist(newRoom);

        assertThat(repository.findById(persisted.getId())).isPresent();
    }

    @Test
    void shouldDeleteRoom() {
        var room = repository.persist(createRoom());

        assertThat(repository.findById(room.getId())).isNotNull();

        repository.deleteByDoorNumber(room.getDoorNumber());

        assertThat(repository.findById(room.getId())).isNotPresent();
    }

    @Test
    void shouldReturnNoRooms() {
        assertThat(repository.findAll()).isEmpty();
    }

    private Room createRoom() {
        var room = new Room();
        room.setId(UUID.randomUUID());
        room.setDoorNumber("123");
        room.setAmount(BigDecimal.TEN);
        room.setRoomType(RoomType.STANDARD);
        return room;
    }

}
