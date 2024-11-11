package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.PriceInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PriceInventoryRepository extends JpaRepository<PriceInventory, UUID> {
    @Query("select p from PriceInventory p")
    List<PriceInventory> findByHotelIdAndCheckin(UUID hotelId, LocalDate checkin);

//    List<PriceInventory> findByHotelIdAndCheckin(Integer hotelId, LocalDate checkin);

}
