package com.indra.StaySmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indra.StaySmart.enums.HotelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "hotel_rating")
    private Double rating;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    // Many-to-Many relationship with Room
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotel_room_mappings",  // Join table name
            joinColumns = @JoinColumn(name = "hotel_id"),  // Column for Hotel
            inverseJoinColumns = @JoinColumn(name = "room_id")  // Column for Room
    )
    @JsonIgnore
    List<Room> roomList = new ArrayList<>();

    // Called before persisting to set timestamps
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    // Called before updating to set updated timestamp
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}
