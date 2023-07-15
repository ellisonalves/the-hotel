package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.RoomJpa;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.mappers.RoomJpaModelMapper;

@Repository
public class RoomJpaRepository implements RoomRepository {

    private final RoomSpringJpaRepository repository;
    private final RoomJpaModelMapper mapper;

    public RoomJpaRepository(RoomSpringJpaRepository roomSpringJpaRepository, RoomJpaModelMapper mapper) {
        this.repository = roomSpringJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Room persist(Room room) {
        return repository.save(mapper.toEntity(room));
    }

    @Override
    public Optional<Room> findById(UUID id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll().stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<Room> findByDoorNumber(String doorNumber) {
        return repository.findByDoorNumber(doorNumber).map(mapper::toEntity);
    }

}

interface RoomSpringJpaRepository extends JpaRepository<RoomJpa, UUID> {

    Optional<RoomJpa> findByDoorNumber(String doorNumber);
}
