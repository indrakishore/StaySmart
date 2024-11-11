package com.indra.StaySmart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@IdClass(HotelRooMappingId.class)
@Table(name = "hotel_room_mappings")
public class HotelRoomMappings {

    @EmbeddedId
    private HotelRoomMappingId id;// Composite key
    //We used @EmbeddedId to mark the primary key, which is an instance of the HotelRooMappingId class.


    @ManyToOne
    @MapsId("hotelId") //@MapsId means that we tie those fields to a part of the key, and they’re the foreign keys of a many-to-one relationship.
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private RoomTypeEntity roomTypeEntity;

    @Column(name = "total_rooms")
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

