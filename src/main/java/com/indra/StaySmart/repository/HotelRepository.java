package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

    Hotel findByHotelId(UUID hotelId);
}
