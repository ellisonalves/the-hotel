package com.ellisonalves.thehotel.domain.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Booking;

public interface BookingRepository {

	Booking persist(Booking booking);

	List<Booking> findBookings(UUID roomId, Instant startDate, Instant endDate);

	Optional<Booking> findById(UUID id);

}
