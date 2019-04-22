package com.ellisonalves.thehotel.usecases;

import com.ellisonalves.thehotel.domain.entities.Room;

public interface PersistRoomsUseCase {
    Room save(Room room);
}
