package com.ellisonalves.thehotel.infrastructure.spring.runner;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.domain.aggregates.AccommodationType;
import com.ellisonalves.thehotel.domain.entity.Guest;
import com.ellisonalves.thehotel.domain.entity.Accommodation;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.domain.repository.AccomodationRepository;

@Component
public class PopulateRepositoryRunner implements CommandLineRunner {

	private final AccomodationRepository roomRepository;

	private final GuestRepository guestRepository;

	public PopulateRepositoryRunner(AccomodationRepository roomRepository, GuestRepository guestRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		persistRoom("Testing Room");
		persistGuest("Testing Guest");
	}

	private void persistGuest(String name) {
		var guest = new Guest();
		guest.setName(name);
		guestRepository.persist(guest);
	}

	private void persistRoom(String doorNumber) {
		var room = new Accommodation();
		room.setDoorNumber(doorNumber);
		room.setAmount(BigDecimal.TEN);
		room.setCurrency(Currency.getInstance("EUR"));
		room.setAccommodationType(AccommodationType.STANDARD);
		roomRepository.persist(room);
	}

}
