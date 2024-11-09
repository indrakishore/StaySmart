package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.Room;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

//    Room findByRoomId(UUID roomId);

    Room findByRoomNameAndHotel_HotelId(String roomName, UUID hotelId);

    Optional<Room> findByRoomId(@NotNull(message = "Room ID is required") UUID roomId);


    void deleteAllByHotel(Hotel hotel);
}
