package com.ellisonalves.thehotel.domain.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Booking;

public interface BookingRepository {

    void persist(Booking booking);

    List<Booking> findBookings(UUID roomId, Instant startDate, Instant endDate);

}
