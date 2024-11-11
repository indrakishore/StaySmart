package com.indra.StaySmart.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class HotelRoomMappingId implements Serializable {
    private UUID hotelId;
    private UUID roomId;

    // Default constructor
    public HotelRoomMappingId() {}

    // Constructor
    public HotelRoomMappingId(UUID hotelId, UUID roomId) {
        this.hotelId = hotelId;
        this.roomId = roomId;
    }

    // Getters and Setters
    public UUID getHotelId() {
        return hotelId;
    }

    public void setHotelId(UUID hotelId) {
        this.hotelId = hotelId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoomMappingId that = (HotelRoomMappingId) o;
        return Objects.equals(hotelId, that.hotelId) && Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, roomId);
    }
}
