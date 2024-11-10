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

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id", nullable = false)
//    private Hotel hotel;

    @ManyToMany(mappedBy = "roomList")
    @JsonIgnore
    private List<Hotel> hotelList = new ArrayList<Hotel>();

}
