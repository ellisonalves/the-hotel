package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.mappers.BookingJpaModelMapper;

@Repository
public class BookingJpaRepository implements BookingRepository {

    private final BookingSpringJpaRepository repository;

    private final BookingJpaModelMapper mapper;

    public BookingJpaRepository(BookingSpringJpaRepository repository, BookingJpaModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void persist(Booking booking) {
        repository.save(mapper.toJpa(booking));
    }

    @Override
    public List<Booking> findBookingsPerRoomAndDateRange(UUID roomId, Instant from, Instant until) {
        return repository.findBookingsPerRoomAndDateRange(roomId, from, until)
                .stream().map(p -> mapper.toDomain(p))
                .toList();
    }

}

interface BookingSpringJpaRepository extends JpaRepository<BookingJpa, UUID> {

    @Query("select b from BookingJpa b where b.roomId = :roomId and (b.startDate <= :endDate and b.endDate >= :startDate)")
    List<BookingJpa> findBookingsPerRoomAndDateRange(
            @Param("roomId") UUID roomId,
            @Param("startDate") Instant start,
            @Param("endDate") Instant end);
}