package com.indra.StaySmart.dto.request;

import java.util.UUID;

public class UpdateTotalRoomsRequestDto {
    private UUID hotelId;
    private UUID roomId;
    private Integer totalRooms;

    // Getters and setters
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

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }
}
