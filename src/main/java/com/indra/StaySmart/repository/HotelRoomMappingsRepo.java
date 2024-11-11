package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.HotelRoomMappingId;
import com.indra.StaySmart.entity.HotelRoomMappings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomMappingsRepo extends JpaRepository<HotelRoomMappings, HotelRoomMappingId> {
    // Custom queries if needed
}

