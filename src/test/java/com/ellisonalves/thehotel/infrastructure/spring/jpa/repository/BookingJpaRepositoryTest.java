package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.ellisonalves.thehotel.annotations.DatabaseTest;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@DatabaseTest
public class BookingJpaRepositoryTest {

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private GuestRepository guestRepo;

	@Autowired
	private RoomRepository roomRepo;

	private final UUID roomId = UUID.fromString("f426b501-4da1-43fa-aaa5-eb63c5cd668f");

	private final Instant start = Instant.parse("2023-07-07T14:00:00.00Z");
	private final Instant end = Instant.parse("2023-07-20T11:00:00.00Z");

	@Test
	@Sql({ "/sql/insert_guest.sql", "/sql/insert_room.sql" })
	void shouldPersistANewBooking() {
		var newBooking = new Booking();
		var guest = guestRepo.findById(UUID.fromString("00250266-30bb-4b54-be0d-cf9f36e59c37"));
		var room = roomRepo.findById(UUID.fromString("f426b501-4da1-43fa-aaa5-eb63c5cd668f"));

		newBooking.setGuest(guest.get());
		newBooking.setRoom(room.get());
		newBooking.setStartDate(start);
		newBooking.setEndDate(end);

		var persisted = bookingRepo.persist(newBooking);

		Optional<Booking> bookingFound = bookingRepo.findById(persisted.getId());

		assertThat(bookingFound).isNotEmpty().hasValueSatisfying(booking -> {
			assertThat(booking.getId()).isNotNull();
			assertThat(booking.getGuest()).isNotNull();
			assertThat(booking.getRoom()).isNotNull();
			assertThat(booking.getStartDate()).isNotNull();
			assertThat(booking.getEndDate()).isNotNull();
			assertThat(booking.getCreatedAt()).isNotNull();
			assertThat(booking.getVersion()).isNotNull();
		});
	}

	@Test
	void shouldNotFindAnyBookingForTheProvidedCriteria() {
		var bookings = bookingRepo.findBookings(roomId, start, end);
		assertThat(bookings).isEmpty();
	}
}
