package com.ellisonalves.thehotel.infrastructure.spring.rest.room.model;

import java.math.BigDecimal;

import com.ellisonalves.thehotel.domain.aggregates.AccommodationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomCreateDto {

    @NotBlank
    private String doorNumber;

    @NotNull
    private AccommodationType roomType;

    @NotNull
    private BigDecimal pricePerDay;

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public AccommodationType getRoomType() {
        return roomType;
    }

    public void setRoomType(AccommodationType type) {
        this.roomType = type;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

}
