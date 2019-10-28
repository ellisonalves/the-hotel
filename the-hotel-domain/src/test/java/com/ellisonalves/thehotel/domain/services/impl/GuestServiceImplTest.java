package com.ellisonalves.thehotel.domain.services.impl;

import com.ellisonalves.thehotel.domain.entities.Guest;
import com.ellisonalves.thehotel.domain.repositories.GuestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GuestServiceImplTest {

    private static final long CUSTOMER_ID = 1L;

    @InjectMocks
    private GuestServiceImpl customerService;

    @Mock
    private GuestRepository guestRepository;

    @Test
    public void saveSuccessfully() {
        Guest guest = new Guest();
        customerService.save(guest);

        verify(guestRepository).save(Mockito.any());
    }

    @Test
    public void findAllSuccessfully() {
        customerService.findAll();

        verify(guestRepository).findAll();
    }

    @Test
    public void findOneThrowsExceptionWhenEntityNotFound() {
        customerService.findOne(CUSTOMER_ID);
        verify(guestRepository).findById(CUSTOMER_ID);
    }

    @Test
    public void findOneSuccessfully() {
        Guest guest = new Guest();
        guest.setId(CUSTOMER_ID);

        Mockito.when(guestRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(guest));

        customerService.findOne(CUSTOMER_ID);
        verify(guestRepository).findById(CUSTOMER_ID);
    }

    @Test
    public void deleteSuccessfully() {
        Guest guest = new Guest();
        guest.setId(CUSTOMER_ID);
        customerService.delete(guest);

        verify(guestRepository).delete(guest);
    }
}