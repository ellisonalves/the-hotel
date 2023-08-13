package com.ellisonalves.thehotel.domain.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.RoomType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room extends BaseEntity<UUID> {

    private static final long serialVersionUID = 1L;

    private String doorNumber;
    private RoomType roomType;
    private Currency currency;
    private BigDecimal amount;
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Override
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

    @Enumerated(EnumType.STRING)
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
