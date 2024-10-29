package com.indra.StaySmart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {
    private UUID hotelRequestId;
    private String hotelName;
    private String hotelAddress;
    private Date createdAt;
    private Date updatedAt;
    private boolean status;
    private String contactNumber;
}
