package com.indra.StaySmart.dto.response;

import com.indra.StaySmart.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDto {

    private UUID roomId;

    private String roomName;

    private RoomType roomType;

    private String amenities;

    private LocalDate createdAt; // Use LocalDate for consistency

    private LocalDate updatedAt; // Use LocalDate for consistency

    @NotNull(message = "Hotel ID cannot be null")
    private UUID hotelId;
}
