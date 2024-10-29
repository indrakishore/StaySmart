package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    Room findByRoomId(UUID roomId);
}
