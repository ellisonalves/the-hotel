package com.ellisonalves.thehotel.infrastructure.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.GuestJpa;

@Repository
public class GuestJpaRepository implements GuestRepository {

    private GuestSpringJpaRepository repository;

    @Autowired
    public GuestJpaRepository(GuestSpringJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Guest guest) {
        repository.save((GuestJpa) guest);
    }

    @Override
    public Optional<Guest> findById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Guest> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    } // extends JpaRepository<Guest, UUID> {
}

interface GuestSpringJpaRepository extends JpaRepository<GuestJpa, UUID> {

}
