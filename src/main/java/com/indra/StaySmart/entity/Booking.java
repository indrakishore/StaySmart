package com.indra.StaySmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indra.StaySmart.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingId;

    @Column(name = "hotel_id")
    private UUID hotelId;

    @Column(name = "room_id")
    private UUID roomId;

    @Column(nullable = false)
    private UUID customerId;

    @Column(nullable = false)
    private UUID inventoryId;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name="booking_amount")
    private Integer bookingAmount;

    @Column(name = "is_prepaid")
    private boolean isPrepaid;

    @Column(name = "checkin")
    private LocalDate checkIn;

    @Column(name = "checkout")
    private LocalDate checkOut;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Customer customer;

}

