package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.time.Instant;
import java.util.List;
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
    public void persist(Booking booking) {
        repository.save(booking);
    }

    @Override
    public List<Booking> findBookingsByRoomAndPeriod(UUID roomId, Instant from, Instant until) {
        return repository.findBookingsPerRoomAndDateRange(roomId, from, until);
    }

}

interface BookingSpringJpaRepository extends JpaRepository<Booking, UUID> {

    @Query("select b from Booking b where b.room.id = :roomId and (b.startDate < :endDate and b.endDate > :startDate)")
    List<Booking> findBookingsPerRoomAndDateRange(UUID roomId, Instant from, Instant until);

}