package com.ellisonalves.thehotel.infrastructure.jpa.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.ellisonalves.thehotel.domain.aggregates.GenderType;
import com.ellisonalves.thehotel.domain.entity.Guest;

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
