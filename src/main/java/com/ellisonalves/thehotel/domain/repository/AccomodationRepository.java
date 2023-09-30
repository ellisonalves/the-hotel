package com.ellisonalves.thehotel.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Accommodation;

public interface AccomodationRepository {

    Accommodation persist(Accommodation room);

    Optional<Accommodation> findById(UUID id);

    void deleteByDoorNumber(String doorNumber);

    List<Accommodation> findAll();

    Optional<Accommodation> findByDoorNumber(String doorNumber);

}
