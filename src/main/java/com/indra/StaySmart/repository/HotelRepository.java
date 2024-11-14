package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Hotel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

    Optional<Hotel> findById(UUID hotelId);

    List<Hotel> findByCity(String city);
}