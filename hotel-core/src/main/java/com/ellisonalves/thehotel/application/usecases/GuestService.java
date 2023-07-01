package com.ellisonalves.thehotel.application.usecases;

import com.ellisonalves.thehotel.domain.entity.Guest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuestService {
    
    void save(Guest guest);

    Optional<Guest> findOne(UUID id);

    List<Guest> findAll();

    void delete(Guest guest);
}
