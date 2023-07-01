package com.ellisonalves.thehotel.infrastructure.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.repository.RoomRepository;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.RoomJpa;

@Repository
public class RoomJpaRepository implements RoomRepository<RoomJpa, UUID> {

    private final RoomSpringJpaRepository repository;

    @Autowired
    public RoomJpaRepository(RoomSpringJpaRepository roomSpringJpaRepository) {
        this.repository = roomSpringJpaRepository;
    }

    @Override
    public void persist(RoomJpa room) {
        repository.save(room);
    }

    @Override
    public Optional<RoomJpa> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<RoomJpa> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RoomJpa> findByDoorNumber(String doorNumber) {
        return repository.findByDoorNumber(doorNumber);
    }

}

interface RoomSpringJpaRepository extends JpaRepository<RoomJpa, UUID> {

    Optional<RoomJpa> findByDoorNumber(String doorNumber);
}
