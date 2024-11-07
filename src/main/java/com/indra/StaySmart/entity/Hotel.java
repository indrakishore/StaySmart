package com.indra.StaySmart.entity;

import com.indra.StaySmart.enums.HotelStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Consider changing this if your DB handles UUID generation
    @Column(name = "hotel_id", unique = true, nullable = false)
    private UUID hotelId;

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @Column(name = "hotel_address", nullable = false)
    private String address;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Enumerated(EnumType.STRING)  // Store status as string value in DB
    @Column(name = "status", nullable = false)
    private HotelStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;
}
