package com.ellisonalves.thehotel.infrastructure.spring.jpa.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.entity.Room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RoomJpa extends Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    @Column(name = "DOOR_NUMBER")
    public String getDoorNumber() {
        return super.getDoorNumber();
    }

    @Override
    @Enumerated(EnumType.STRING)
    public RoomType getRoomType() {
        return super.getRoomType();
    }

    @Override
    public BigDecimal getAmount() {
        return super.getAmount();
    }

}
