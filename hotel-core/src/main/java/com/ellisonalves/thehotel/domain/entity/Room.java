package com.ellisonalves.thehotel.domain.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

public class Room extends BaseEntity<UUID> {

    private UUID id;
    private String doorNumber;
    private RoomType roomType;
    private Currency currency;
    private BigDecimal pricePerDay;
    private Long version;

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equalTo(Object o) {
        Room other = (Room) o;
        return Objects.equals(this.getId(), other.getId());
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCodePrime() {
        return 41;
    }

}
