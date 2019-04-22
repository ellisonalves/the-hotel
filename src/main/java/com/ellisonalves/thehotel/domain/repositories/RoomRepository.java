package com.ellisonalves.thehotel.domain.repositories;

import com.ellisonalves.thehotel.domain.entities.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, String> {
}
