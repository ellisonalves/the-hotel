package com.ellisonalves.thehotel.infrastructure.controller.room;

import java.math.BigDecimal;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomUpdateDto {

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
