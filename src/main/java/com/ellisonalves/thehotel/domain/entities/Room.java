package com.ellisonalves.thehotel.domain.entities;

import com.ellisonalves.thehotel.domain.types.RoomType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Room {
    @Id
    private String doorNumber;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    private BigDecimal pricePerDay;
}
