package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.ellisonalves.thehotel.config.JpaRepositoryTestConfig;
import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.RoomJpa;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.repository.RoomJpaRepository;

@DataJpaTest
@Import(JpaRepositoryTestConfig.class)
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
        repository.persist(newRoom);

        assertThat(repository.findById(newRoom.getId())).isPresent();
    }

    @Test
    void shouldDeleteRoom() {
        var room = createRoom();
        repository.persist(room);

        assertThat(repository.findById(room.getId())).isNotNull();

        repository.deleteById(room.getId());

        assertThat(repository.findById(room.getId()));
    }

    @Test
    void shouldReturnNoRooms() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void shouldReturnPersistedRooms() throws InterruptedException {
        repository.persist(createRoom());
        repository.persist(createRoom());
        repository.persist(createRoom());

        assertThat(repository.findAll()).hasSize(3);
    }

    private RoomJpa createRoom() {
        var room = new RoomJpa();
        room.setId(UUID.randomUUID());
        room.setDoorNumber("123");
        room.setPricePerDay(BigDecimal.TEN);
        room.setRoomType(RoomType.STANDARD);
        return room;
    }

}
