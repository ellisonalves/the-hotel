package com.ellisonalves.thehotel.usecases;

import com.ellisonalves.thehotel.domain.entities.Room;

import java.util.List;

public interface FindRoomsUseCase {

    Room findOne(Integer doorNumber);

    List<Room> findAll();

}
