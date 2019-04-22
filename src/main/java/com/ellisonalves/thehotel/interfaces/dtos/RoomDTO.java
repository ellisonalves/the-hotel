package com.ellisonalves.thehotel.interfaces.dtos;

import com.ellisonalves.thehotel.domain.types.RoomType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDTO {
    private Integer doorNumber;
    private RoomType type;
    private BigDecimal pricePerDay;
}
