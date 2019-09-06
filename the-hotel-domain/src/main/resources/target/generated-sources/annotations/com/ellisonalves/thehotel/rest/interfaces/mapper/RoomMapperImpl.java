package com.ellisonalves.thehotel.rest.interfaces.mapper;

import com.ellisonalves.thehotel.domain.entities.Room;
import com.ellisonalves.thehotel.rest.interfaces.dtos.RoomDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2019-09-01T05:19:20+0200",
        comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.4 (Ubuntu)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDTO toDTO(Room entity) {
        if (entity == null) {
            return null;
        }

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setDoorNumber(entity.getDoorNumber());
        roomDTO.setType(entity.getType());
        roomDTO.setPricePerDay(entity.getPricePerDay());

        return roomDTO;
    }

    @Override
    public Room toEntity(RoomDTO dto) {
        if (dto == null) {
            return null;
        }

        Room room = new Room();

        room.setDoorNumber(dto.getDoorNumber());
        room.setType(dto.getType());
        room.setPricePerDay(dto.getPricePerDay());

        return room;
    }
}
