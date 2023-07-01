package com.ellisonalves.thehotel.services.impl;

import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.GuestJpa;
import com.ellisonalves.thehotel.infrastructure.jpa.repository.GuestRepository;
import com.ellisonalves.thehotel.infrastructure.service.GuestServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GuestServiceImplTest {

    // private static final UUID CUSTOMER_ID = UUID.randomUUID();

    // @InjectMocks
    // private GuestServiceImpl customerService;

    // @Mock
    // private GuestRepository guestRepository;

    // @Test
    // public void saveSuccessfully() {
    //     Guest guest = new GuestJpa();
    //     customerService.save(guest);

    //     verify(guestRepository).save(Mockito.any());
    // }

    // @Test
    // public void findAllSuccessfully() {
    //     customerService.findAll();

    //     verify(guestRepository).findAll();
    // }

    // @Test
    // public void findOneThrowsExceptionWhenEntityNotFound() {
    //     customerService.findOne(CUSTOMER_ID);
    //     verify(guestRepository).findById(CUSTOMER_ID);
    // }

    // @Test
    // public void findOneSuccessfully() {
    //     var guest = new GuestJpa();
    //     guest.setId(CUSTOMER_ID);

    //     Mockito.when(guestRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(guest));

    //     customerService.findOne(CUSTOMER_ID);
    //     verify(guestRepository).findById(CUSTOMER_ID);
    // }

    // @Test
    // public void deleteSuccessfully() {
    //     var guest = new GuestJpa();
    //     guest.setId(CUSTOMER_ID);
    //     customerService.delete(guest);

    //     verify(guestRepository).delete(guest);
    // }
}