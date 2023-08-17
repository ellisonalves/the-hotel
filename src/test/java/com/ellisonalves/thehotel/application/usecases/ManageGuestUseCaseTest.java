package com.ellisonalves.thehotel.application.usecases;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;

@ExtendWith(MockitoExtension.class)
public class ManageGuestUseCaseTest {

    @InjectMocks
    private ManageGuestUseCase useCase;

    @Mock
    private GuestRepository repository;

    @Test
    void testDelete() {
        var id = UUID.randomUUID();
        useCase.delete(id);

        verify(repository, only()).delete(id);
    }

    @Test
    void testFindAll() {
        useCase.findAll();
        verify(repository, only()).findAll();
    }

    @Test
    void testFindById() {
        var id = UUID.randomUUID();
        useCase.findById(id);
        verify(repository, only()).findById(id);
    }

    @Test
    void testSave() {
        var guest = new GuestStub();
        useCase.save(guest);

        verify(repository, only()).persist(guest);
    }

    private static class GuestStub extends Guest {

		private static final long serialVersionUID = 1L;
    }
}
