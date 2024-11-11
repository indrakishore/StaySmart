package com.indra.StaySmart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel_room_mappings")
public class HotelRoomMappings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hotel_id")
    private UUID hotelId;

    @Column(name = "room_id")
    private UUID roomId;

    @Column(name = "total_rooms", nullable = false)
    private Integer totalRooms;

    // Many-to-One relationship with Hotel
//    @ManyToOne
//    @JoinColumn(name = "hotel_id", nullable = false)
//    private Hotel hotel;

    // Many-to-One relationship with Room
//    @ManyToOne
//    @JoinColumn(name = "room_id", nullable = false)
//    private Room room;


}

