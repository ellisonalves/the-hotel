package com.ellisonalves.thehotel.domain.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Guest;

public interface GuestRepository extends Persistable<Guest, UUID> {

    public void save(Guest guest);

    public Optional<Guest> findById(UUID id);

    public void delete(UUID id);

    public List<Guest> findAll();

}

interface Persistable<T extends Serializable, ID extends Serializable> {
}
