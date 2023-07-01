package com.ellisonalves.thehotel.infrastructure.controller;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

public class RoomRequest {

    @NotBlank
    private String doorNumber;

    @NotNull
    private RoomType type;

    @NotNull
    private BigDecimal pricePerDay;

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

}
