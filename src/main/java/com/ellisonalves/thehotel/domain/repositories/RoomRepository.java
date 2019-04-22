package com.ellisonalves.thehotel.domain.repositories;

import com.ellisonalves.thehotel.domain.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
}
