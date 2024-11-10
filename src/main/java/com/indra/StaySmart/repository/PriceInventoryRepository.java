package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.PriceInventory;
import com.indra.StaySmart.entity.Room;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceInventoryRepository extends JpaRepository<PriceInventory, UUID> {

//    List<PriceInventory> findByHotelIdAndCheckin(Integer hotelId, LocalDate checkin);

}
