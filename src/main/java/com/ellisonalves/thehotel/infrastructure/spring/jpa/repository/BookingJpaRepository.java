package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;

@Repository
public class BookingJpaRepository implements BookingRepository {

	private final BookingSpringJpaRepository repository;

	public BookingJpaRepository(BookingSpringJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Booking persist(Booking booking) {
		return repository.save(booking);
	}

	@Override
	public List<Booking> findBookings(UUID roomId, Instant startDate, Instant endDate) {
		return repository.findBookingsPerRoomAndDateRange(roomId, startDate, endDate);
	}

	@Override
	public Optional<Booking> findById(UUID id) {
		return repository.findById(id);
	}

}

interface BookingSpringJpaRepository extends JpaRepository<Booking, UUID> {

	@Query("from Booking b where b.room.id = :roomId and (b.startDate < :endDate and b.endDate > :startDate)")
	List<Booking> findBookingsPerRoomAndDateRange(UUID roomId, Instant startDate, Instant endDate);

}