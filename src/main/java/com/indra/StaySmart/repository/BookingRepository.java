package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.Booking;
import com.indra.StaySmart.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Optional<Booking> findByBookingId(UUID bookingId);

    @Query("Select b.customer.customerId " +
            "FROM Booking b " +
            "Group By b.customer.customerId, b.hotelId " +
            "Having COUNT(b) > 1")
    List<UUID> findCustomerWithMultipleBooking();

    @Query("Select distinct c from Customer c where c.customerId in :customerIds")
    List<Customer> findCustomersById(List<UUID> customerIds);
}
