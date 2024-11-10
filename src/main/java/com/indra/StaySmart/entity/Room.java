package com.indra.StaySmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indra.StaySmart.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
@Table(name="room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID roomId;

    @Column(name = "room_name")
    private String roomName;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "amenities")
    private String amenities;

    @Column(name="max_occupancy")
    private Integer maxOccupancy;

    @Column(name="active")
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    // Many-to-Many relationship with Hotel (Inverse side of the relationship)
    @ManyToMany(mappedBy = "roomList")
    @JsonIgnore
    private List<Hotel> hotelList = new ArrayList<>();

    // One-to-Many relationship with HotelRoomMappings
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<HotelRoomMappings> hotelRoomMappings = new ArrayList<>();

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
