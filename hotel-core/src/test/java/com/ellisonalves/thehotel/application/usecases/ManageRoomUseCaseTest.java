package com.ellisonalves.thehotel.application.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.application.usecases.ManageRoomUseCase;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
public class ManageRoomUseCaseTest {

    @InjectMocks
    private ManageRoomUseCase useCase;

    @Mock
    private RoomRepository repository;

    private UUID roomId = UUID.randomUUID();

    private String doorNumber = "123";

    private Room defaultRoom = new RoomStub();

    @Test
    public void shouldPersitRoom() {
        var room = new RoomStub();
        useCase.save(room);

        verify(repository, only()).persist(room);
    }

    @Test
    public void shouldFindRoomById() {
        when(repository.findById(roomId)).thenReturn(Optional.of(defaultRoom));
        useCase.findById(roomId);

        verify(repository, only()).findById(roomId);
    }

    @Test
    public void shouldRaiseExceptionWhenFindByRoomById() {
        when(repository.findById(roomId)).thenThrow(new ResourceNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> {
            useCase.findById(roomId);
        });
    }

    @Test
    public void shouldFindRoomByDoorNumberO() {
        when(repository.findByDoorNumber(doorNumber)).thenReturn(Optional.of(defaultRoom));
        useCase.findByDoorNumber(doorNumber);

        verify(repository, only()).findByDoorNumber(doorNumber);
    }

    @Test
    public void shouldFindAllRooms() {
        useCase.findAll();
        verify(repository, only()).findAll();
    }

    private static class RoomStub extends Room {
    }

}
