package com.ellisonalves.thehotel.application.usecases;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.GuestId;
import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;

public class ManageGuestUseCase {

    private final GuestRepository repository;

    public ManageGuestUseCase(GuestRepository repository) {
        this.repository = repository;
    }

    public void save(Guest guest) {
        repository.persist(guest);
    }

    public Optional<Guest> findById(UUID id) {
        return repository.findById(id);
    }

    public List<Guest> findAll() {
        return repository.findAll();
    }

    void delete(UUID id) {
        repository.delete(id);
    }

    public boolean guestHasAnotherBook(GuestId guestId) {
        return false;
    }

}
