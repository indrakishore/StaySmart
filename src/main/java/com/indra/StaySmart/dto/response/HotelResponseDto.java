package com.indra.StaySmart.dto.response;

import com.indra.StaySmart.entity.Room;
import com.indra.StaySmart.enums.HotelStatus;
import lombok.*;

import java.time.LocalDate;
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

    private Double rating;

    private String contactNumber;

    private String errorMessage;

    List<Room> rooms;

//    private LocalDate createdAt; // Use LocalDate
//
//    private LocalDate updatedAt; // Use LocalDate
//
//    private HotelStatus status; // Use an enum for better flexibility


}
