package com.ellisonalves.thehotel.domain.services;

import com.ellisonalves.thehotel.domain.entities.Guest;

import java.util.List;

public interface GuestService extends CRUDService<Guest, Long> {
    List<Guest> findAll();
}
