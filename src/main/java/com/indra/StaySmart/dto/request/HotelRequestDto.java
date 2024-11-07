package com.indra.StaySmart.dto.request;

import com.indra.StaySmart.enums.HotelStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelRequestDto {

    private UUID hotelId;

    @NotNull
    @Size(min = 1, max = 100)
    private String hotelName;

    @NotNull
    @Size(min = 1, max = 200)
    private String hotelAddress;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @Enumerated(EnumType.STRING)
    private HotelStatus status; // Change to enum for more flexibility

    @NotNull
    @Pattern(regexp = "^[0-9]{10,15}$")  // Regex for valid phone number format
    private String contactNumber;

}

