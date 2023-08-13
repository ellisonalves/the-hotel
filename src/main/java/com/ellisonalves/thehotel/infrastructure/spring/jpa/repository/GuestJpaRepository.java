package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.GuestJpa;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.mappers.GuestJpaModelMapper;

@Repository
public class GuestJpaRepository implements GuestRepository {

    private final GuestSpringJpaRepository repository;
    private final GuestJpaModelMapper mapper;

    public GuestJpaRepository(GuestSpringJpaRepository repository, GuestJpaModelMapper guestMapper) {
        this.repository = repository;
        this.mapper = guestMapper;
    }

    @Override
    public void persist(Guest guest) {
        repository.save(mapper.toJpa(guest));
    }

    @Override
    public Optional<Guest> findById(UUID id) {
        return repository.findById(id).map(m -> mapper.toDomain(m));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Guest> findAll() {
        return repository.findAll()
                .stream()
                .map(guest -> mapper.toDomain(guest))
                .collect(Collectors.toList());
    }
}

interface GuestSpringJpaRepository extends JpaRepository<GuestJpa, UUID> {

}
