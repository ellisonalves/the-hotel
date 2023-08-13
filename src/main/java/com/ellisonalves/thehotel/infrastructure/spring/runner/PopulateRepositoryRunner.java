package com.ellisonalves.thehotel.infrastructure.spring.runner;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.RoomJpa;

@Component
public class PopulateRepositoryRunner implements CommandLineRunner {

    private final RoomRepository roomRepository;

    public PopulateRepositoryRunner(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        persistRoom("123A");
        persistRoom("123B");
        persistRoom("123C");
    }

    private void persistRoom(String doorNumber) {
        var room = new RoomJpa();
        room.setDoorNumber(doorNumber);
        room.setAmount(BigDecimal.TEN);
        room.setCurrency(Currency.getInstance("EUR"));
        room.setRoomType(RoomType.STANDARD);
        roomRepository.persist(room);
    }

}
