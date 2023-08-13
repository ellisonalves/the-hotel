package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;

@Repository
public class GuestJpaRepository implements GuestRepository {

    private final GuestSpringJpaRepository repository;

    public GuestJpaRepository(GuestSpringJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void persist(Guest guest) {
        repository.save(guest);
    }

    @Override
    public Optional<Guest> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Guest> findAll() {
        return repository.findAll()
                .stream()
                .collect(Collectors.toList());
    }
}

interface GuestSpringJpaRepository extends JpaRepository<Guest, UUID> {

}
