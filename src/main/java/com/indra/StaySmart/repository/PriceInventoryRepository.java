package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.PriceInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceInventoryRepository extends JpaRepository<PriceInventory, UUID> {

//    List<PriceInventory> findByHotelIdAndCheckin(Integer hotelId, LocalDate checkin);

}
