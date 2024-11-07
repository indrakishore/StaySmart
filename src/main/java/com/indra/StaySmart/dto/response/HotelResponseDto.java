package com.indra.StaySmart.dto.response;

import com.indra.StaySmart.enums.HotelStatus;
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
public class HotelResponseDto {

    private UUID hotelId; // Change to hotelId for consistency

    private String hotelName;

    private String hotelAddress;

    private LocalDate createdAt; // Use LocalDate

    private LocalDate updatedAt; // Use LocalDate

    private HotelStatus status; // Use an enum for better flexibility

    private String contactNumber;
}
