package com.ellisonalves.thehotel.domain.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Booking extends BaseEntity<UUID> {

    private UUID id;
    private UUID guestId;
    private UUID roomId;
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

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuest(UUID guestId) {
        this.guestId = guestId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
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

}
