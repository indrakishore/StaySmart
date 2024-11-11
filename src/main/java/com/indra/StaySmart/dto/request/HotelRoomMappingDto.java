package com.indra.StaySmart.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class HotelRoomMappingDto {

    private UUID hotelId;
    private UUID roomId;  // ID of the room type
    private int totalRooms;  // Total number of rooms for that room type


}

