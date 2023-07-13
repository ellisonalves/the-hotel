package com.ellisonalves.thehotel.infrastructure.spring.jpa.repository;

import java.time.Instant;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.entity.Booking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    public UUID getRoomId() {
        return super.getRoomId();
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
