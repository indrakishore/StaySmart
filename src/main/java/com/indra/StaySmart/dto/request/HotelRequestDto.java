package com.indra.StaySmart.dto.request;

import com.indra.StaySmart.enums.HotelStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class HotelRequestDto {

    private UUID hotelId;

    private String hotelName;

    private String hotelAddress;

    @NotNull
    @Pattern(regexp = "^[0-9]{10,15}$")  // Regex for valid phone number format
    private String contactNumber;

    private Double rating;

    @Enumerated(EnumType.STRING)
    private HotelStatus status; // Change to enum for more flexibility

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private List<HotelRoomMappingDto> roomMappings;  // List of mappings

}

