package com.indra.StaySmart.dto.response;

import com.indra.StaySmart.enums.HotelStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {

    private UUID hotelId; // Change to hotelId for consistency

    private String hotelName;

    private String hotelAddress;

    private Double rating;

    private LocalDate createdAt; // Use LocalDate

    private LocalDate updatedAt; // Use LocalDate

    private HotelStatus status; // Use an enum for better flexibility

    private String contactNumber;


    public HotelResponseDto(UUID hotelId, String hotelName, String address, LocalDate createdAt, LocalDate updatedAt, HotelStatus status, String contactNumber, Double rating) {
    }
}
