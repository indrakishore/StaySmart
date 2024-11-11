package com.indra.StaySmart.dto.response;

import com.indra.StaySmart.enums.RoomCategory;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RoomTypeResponseDto {

//    @NotNull(message = "Hotel ID cannot be null")
//    private UUID hotelId;

    private UUID roomId;

//    private String roomName;

    private RoomCategory roomCategory;

    private String amenities;

//    private Integer totalRooms;

//    private LocalDate createdAt; // Use LocalDate for consistency
//
//    private LocalDate updatedAt; // Use LocalDate for consistency

}
