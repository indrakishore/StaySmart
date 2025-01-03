package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Optional<Booking> findByBookingId(UUID bookingId);
}
