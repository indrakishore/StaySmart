package com.indra.StaySmart.dto.response;

import com.indra.StaySmart.entity.RoomTypeEntity;
import com.indra.StaySmart.enums.HotelStatus;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HotelResponseDto {

    private UUID hotelId;

    private String hotelName;

    private String hotelAddress;

    private String city;

    private Double rating;

    private String contactNumber;

    private List<RoomTypeEntity> rooms;  // The Room Types associated with the hotel

//    private List<RoomResponseDto> rooms;


//    private String errorMessage;


//    private LocalDate createdAt; // Use LocalDate
//
//    private LocalDate updatedAt; // Use LocalDate
//
    private HotelStatus status; // Use an enum for better flexibility


}
