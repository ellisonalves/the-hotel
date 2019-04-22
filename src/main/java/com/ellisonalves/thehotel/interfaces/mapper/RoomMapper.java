package com.ellisonalves.thehotel.interfaces.mapper;

import com.ellisonalves.thehotel.domain.entities.Room;
import com.ellisonalves.thehotel.interfaces.dtos.RoomDTO;
import com.ellisonalves.thehotel.interfaces.dtos.RoomListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper extends BaseMapper<Room, RoomDTO> {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    default RoomListDTO toRoomListDTO(List<Room> rooms) {
        return new RoomListDTO(toDTOList(rooms));
    }

}
