package com.indra.StaySmart.dto.request;

import com.indra.StaySmart.enums.RoomCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RoomTypeRequestDto {

    private UUID roomId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomCategory roomCategory;

    private String amenities;

    private Integer maxOccupancy;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @NotNull(message = "Hotel ID cannot be null")
    private UUID hotelId;  // Ensure this links the room to a specific hotel

    private Integer totalRooms;

}
