package com.ellisonalves.thehotel.infrastructure.spring.runner;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@Component
public class PopulateRepositoryRunner implements CommandLineRunner {

	private final RoomRepository roomRepository;

	private final GuestRepository guestRepository;

	public PopulateRepositoryRunner(RoomRepository roomRepository, GuestRepository guestRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		persistRoom("123Teste");
		persistGuest();
	}

	private void persistGuest() {
		var guest = new Guest();
		guest.setName("Testing Guest");

		guestRepository.persist(guest);
	}

	private void persistRoom(String doorNumber) {
		var room = new Room();
		room.setDoorNumber(doorNumber);
		room.setAmount(BigDecimal.TEN);
		room.setCurrency(Currency.getInstance("EUR"));
		room.setRoomType(RoomType.STANDARD);
		roomRepository.persist(room);
	}

}
