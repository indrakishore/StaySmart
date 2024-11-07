package com.indra.StaySmart.entity;

import com.indra.StaySmart.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private UUID bookingId = UUID.randomUUID();

    private UUID userId;

    private UUID hotelId;

    private UUID roomId;

    private boolean isPrepaid;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}

