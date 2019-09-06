package com.ellisonalves.thehotel.rest.interfaces.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoomListDTO {
    private List<RoomDTO> rooms;
}
