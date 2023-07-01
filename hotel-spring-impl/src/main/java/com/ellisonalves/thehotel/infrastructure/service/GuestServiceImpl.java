package com.ellisonalves.thehotel.infrastructure.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ellisonalves.thehotel.application.usecases.GuestService;
import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.infrastructure.jpa.repository.GuestRepository;

//@Service
public class GuestServiceImpl implements GuestService {

    public final GuestRepository repository;

  //  @Autowired
    GuestServiceImpl(GuestRepository repository) {
        this.repository = repository;
    }

//    @Override
    public void save(Guest guest) {
        //repository.save(guest);
    }

 //   @Override
    public Optional<Guest> findOne(UUID id) {
        return null;
        //return repository.findById(id);
    }

//    @Override
    public List<Guest> findAll() {
        return null;
       // return repository.findAll();
    }

//    @Override
    public void delete(Guest guest) {
       // repository.delete(guest);
    }

}
