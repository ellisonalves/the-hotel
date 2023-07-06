package com.ellisonalves.thehotel.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.ellisonalves.thehotel.domain.aggregates.GuestId;
import com.ellisonalves.thehotel.domain.aggregates.RoomId;

public class Booking extends BaseEntity<GuestId> {

    private GuestId id;
    private GuestId guestId;
    private RoomId roomId;
    private LocalDateTime from;
    private LocalDateTime until;
    private Long version;

    @Override
    public GuestId getId() {
        return this.id;
    }

    public void setId(GuestId id) {
        this.id = id;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public void setGuest(GuestId guestId) {
        this.guestId = guestId;
    }

    public RoomId getRoomId() {
        return roomId;
    }

    public void setRoomId(RoomId roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getUntil() {
        return until;
    }

    public void setUntil(LocalDateTime until) {
        this.until = until;
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
