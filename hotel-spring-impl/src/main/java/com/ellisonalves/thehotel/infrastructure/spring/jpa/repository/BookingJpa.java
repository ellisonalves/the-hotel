package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.time.Instant;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.GuestJpa;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.RoomJpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class BookingJpa extends Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    @ManyToOne(optional = false)
    public RoomJpa getRoom() {
        return (RoomJpa) super.getRoom();
    }

    @Override
    @ManyToOne(optional = false)
    public GuestJpa getGuest() {
        return (GuestJpa) super.getGuest();
    }

    @Override
    @Temporal(TemporalType.TIMESTAMP)
    public Instant getEndDate() {
        return super.getEndDate();
    }

    @Override
    @Temporal(TemporalType.TIMESTAMP)
    public Instant getStartDate() {
        return super.getStartDate();
    }

}
