package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ellisonalves.thehotel.annotations.DatabaseTest;
import com.ellisonalves.thehotel.domain.aggregates.AccommodationType;
import com.ellisonalves.thehotel.domain.entity.Accommodation;

@DatabaseTest
class AccommodationJpaRepositoryTest {

	@Autowired
	private AccommodationJpaRepository repository;

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

	private Accommodation createRoom() {
		var room = new Accommodation();
		room.setDoorNumber("123");
		room.setAmount(BigDecimal.TEN);
		room.setAccommodationType(AccommodationType.STANDARD);
		return room;
	}

}
