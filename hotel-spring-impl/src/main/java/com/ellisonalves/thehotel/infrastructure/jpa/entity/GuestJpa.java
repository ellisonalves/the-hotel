package com.ellisonalves.thehotel.infrastructure.jpa.entity;

import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.GenderType;
import com.ellisonalves.thehotel.domain.entity.Guest;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class GuestJpa extends Guest {

    @Id
    @Override
    public UUID getId() {
        return super.getId();
    }

    @Enumerated(EnumType.STRING)
    @Override
    public GenderType getGenderType() {
        return super.getGenderType();
    }

}
