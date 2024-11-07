package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.PricingInventory;
import com.indra.StaySmart.entity.Room;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface InventoryPricingRepository extends JpaRepository<PricingInventory, UUID> {


    Optional<PricingInventory> findByHotelAndRoomAndDate(Hotel hotel, Room room, @NotNull(message = "Start date is required") LocalDate startDate);
}
