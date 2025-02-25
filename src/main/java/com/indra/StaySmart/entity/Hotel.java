package com.indra.StaySmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.indra.StaySmart.enums.HotelStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
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

    @Column
    private String city;

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
    @JsonIgnore //to avoid jackson recursion
    private List<RoomTypeEntity> roomTypeEntityList = new ArrayList<>(); // List of rooms in the hotel

    // One-to-Many relationship with HotelRoomMappings
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @JsonManagedReference  // Serialize hotelRoomMappings
    private List<HotelRoomMappings> hotelRoomMappings = new ArrayList<>(); // List of mappings between hotel and room

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
