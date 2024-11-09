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
@ToString
public class HotelRequestDto {

    private UUID hotelId;

    @NotNull
    private String hotelName;

    @NotNull
    private String hotelAddress;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private Double rating;

    @Enumerated(EnumType.STRING)
    private HotelStatus status; // Change to enum for more flexibility

    @NotNull
    @Pattern(regexp = "^[0-9]{10,15}$")  // Regex for valid phone number format
    private String contactNumber;

    public HotelRequestDto(UUID hotelId, String hotelName, String hotelAddress, LocalDate createdAt, LocalDate updatedAt, Double rating, HotelStatus status, String contactNumber) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.rating = rating;
        this.status = status;
        this.contactNumber = contactNumber;
    }
}

