package com.indra.StaySmart.dto.request;

import com.indra.StaySmart.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDto {

    private
    UUID roomId;

    @NotNull
    private String roomName;

    @NotNull
    private RoomType roomType;

    private String amenities;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @NotNull(message = "Hotel ID cannot be null")
    private UUID hotelId;

}
