package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, UUID> {

    RoomTypeEntity findByRoomId(UUID roomId);

//    Room findByRoomNameAndHotel_HotelId(String roomName, UUID hotelId);

//    Optional<Room> findByRoomId(@NotNull(message = "Room ID is required") UUID roomId);


//    void deleteAllByHotel(Hotel hotel);
}
