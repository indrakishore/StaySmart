package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.HotelRoomMappingId;
import com.indra.StaySmart.entity.HotelRoomMappings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface HotelRoomMappingsRepo extends JpaRepository<HotelRoomMappings, HotelRoomMappingId> {

    @Query("SELECT hrm FROM HotelRoomMappings hrm WHERE hrm.hotel.hotelId = :hotelId AND hrm.roomTypeEntity.roomId = :roomId")
    Optional<HotelRoomMappings> findByHotelIdAndRoomTypeId(@Param("hotelId") UUID hotelId, @Param("roomId") UUID roomId);

    @Query("DELETE FROM HotelRoomMappings hrm WHERE hrm.hotel.hotelId = :hotelId")
    void deleteByHotelId(@Param("hotelId") UUID hotelId);

}



