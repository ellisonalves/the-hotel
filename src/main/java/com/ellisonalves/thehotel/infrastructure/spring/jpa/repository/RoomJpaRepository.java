package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@Repository
public class RoomJpaRepository implements RoomRepository {

	private final RoomSpringJpaRepository repository;

	public RoomJpaRepository(RoomSpringJpaRepository roomSpringJpaRepository) {
		this.repository = roomSpringJpaRepository;
	}

	@Override
	public Room persist(Room room) {
		return repository.save(room);
	}

	@Override
	public Optional<Room> findById(UUID id) {
		return repository.findById(id);
	}

	@Override
	public void deleteByDoorNumber(String doorNumber) {
		repository.deleteByDoorNumber(doorNumber);
	}

	@Override
	public List<Room> findAll() {
		return repository.findAll().stream().toList();
	}

	@Override
	public Optional<Room> findByDoorNumber(String doorNumber) {
		return repository.findByDoorNumber(doorNumber);
	}

}

interface RoomSpringJpaRepository extends JpaRepository<Room, UUID> {

	Optional<Room> findByDoorNumber(String doorNumber);

	void deleteByDoorNumber(String doorNumber);
}
