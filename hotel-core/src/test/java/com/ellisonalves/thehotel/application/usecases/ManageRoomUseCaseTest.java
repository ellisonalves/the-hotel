package com.ellisonalves.thehotel.application.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.application.usecases.room.CreateRoomDto;
import com.ellisonalves.thehotel.application.usecases.room.ManageRoomUseCase;
import com.ellisonalves.thehotel.application.usecases.room.Money;
import com.ellisonalves.thehotel.application.usecases.room.RoomMapper;
import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
public class ManageRoomUseCaseTest {

    @InjectMocks
    private ManageRoomUseCase useCase;

    @Mock
    private RoomRepository repository;

    @Mock
    private RoomMapper roomMapper;

    private UUID roomId = UUID.randomUUID();

    private String doorNumber = "123";

    private CreateRoomDto room = new CreateRoomDto(
            doorNumber,
            RoomType.STANDARD,
            new Money(
                    new BigDecimal("20"),
                    Currency.getInstance("EUR"))

    );

    @Test
    public void shouldPersitRoom() {
        Room expected = new Room();
        when(roomMapper.toDomain(room)).thenReturn(expected);

        useCase.persist(room);

        verify(repository, only()).persist(expected);
    }

    @Test
    public void shouldFindRoomById() {
        // when(repository.findById(roomId)).thenReturn(Optional.of(room));
        // useCase.findById(roomId);

        // verify(repository, only()).findById(roomId);
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
        // when(repository.findByDoorNumber(doorNumber)).thenReturn(Optional.of(room));
        // useCase.findByDoorNumber(doorNumber);

        // verify(repository, only()).findByDoorNumber(doorNumber);
    }

    @Test
    public void shouldFindAllRooms() {
        useCase.findAll();
        verify(repository, only()).findAll();
    }

}
