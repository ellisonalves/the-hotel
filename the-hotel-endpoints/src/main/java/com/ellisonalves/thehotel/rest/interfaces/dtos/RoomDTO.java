package com.ellisonalves.thehotel.rest.interfaces.dtos;

import com.ellisonalves.thehotel.domain.types.RoomType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class RoomDTO {

    @NotNull
    private Integer doorNumber;

    @NotNull
    private RoomType type;

    @PositiveOrZero
    private BigDecimal pricePerDay;
}
