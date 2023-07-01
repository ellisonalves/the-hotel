package com.ellisonalves.thehotel.infrastructure.jpa.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.entity.Room;

@Entity
public class RoomJpa extends Room {

    @Id
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
    public BigDecimal getPricePerDay() {
        return super.getPricePerDay();
    }

}
