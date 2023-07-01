package com.ellisonalves.thehotel.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

public abstract class Room implements Serializable {

    private UUID id;

    private String doorNumber;

    private RoomType roomType;

    private BigDecimal pricePerDay;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType type) {
        this.roomType = type;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

}
