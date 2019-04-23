package com.ellisonalves.thehotel.domain.services.impl;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.domain.entities.Room;
import com.ellisonalves.thehotel.domain.repositories.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {

    private static final Integer ROOM_DOOR_NUMBER = 100;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Mock
    private RoomRepository roomRepository;

    @Test
    public void findAllSuccessfully() {
        roomService.findAll();

        verify(roomRepository).findAll();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOneThrowsExceptionWhenEntityNotFound() {
        roomService.findOne(ROOM_DOOR_NUMBER);
        verify(roomRepository).findById(ROOM_DOOR_NUMBER);
    }

    @Test
    public void findOneSuccessfully() {
        Room room = new Room();
        room.setDoorNumber(ROOM_DOOR_NUMBER);

        Mockito.when(roomRepository.findById(ROOM_DOOR_NUMBER)).thenReturn(Optional.of(room));

        roomService.findOne(ROOM_DOOR_NUMBER);
        verify(roomRepository).findById(ROOM_DOOR_NUMBER);
    }

    @Test
    public void saveSuccessfully() {
        Room room = new Room();
        roomService.save(room);

        verify(roomRepository).save(Mockito.any());
    }

    @Test
    public void deleteSuccessfully() {
        Room room = new Room();
        room.setDoorNumber(ROOM_DOOR_NUMBER);
        roomService.delete(room);

        verify(roomRepository).delete(room);
    }
}