package com.ellisonalves.thehotel.domain.services.impl;

import com.ellisonalves.thehotel.domain.entities.Guest;
import com.ellisonalves.thehotel.domain.repositories.GuestRepository;
import com.ellisonalves.thehotel.domain.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;

    @Autowired
    GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> findOne(Long id) {
        return guestRepository.findById(id);
    }

    @Override
    public void delete(Guest guest) {
        guestRepository.delete(guest);
    }

}
