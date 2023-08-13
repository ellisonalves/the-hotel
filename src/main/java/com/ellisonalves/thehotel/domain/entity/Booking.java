package com.ellisonalves.thehotel.domain.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Booking extends BaseEntity<UUID> {

    private static final long serialVersionUID = 1L;
    
	private UUID id;
    private Guest guest;
    private Room room;
    private Instant startDate;
    private Instant endDate;
    private Long version;

    @Override
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equalTo(Object o) {
        Booking other = (Booking) o;
        return Objects.equals(id, other.getId());
    }

    @Override
    public int hashCodePrime() {
        return 31;
    }

}
