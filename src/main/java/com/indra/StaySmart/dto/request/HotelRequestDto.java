package com.indra.StaySmart.dto.request;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotelRequestDto {

    private UUID hotelRequestId;
    private String hotelName;
    private String hotelAddress;
    private Date createdAt;
    private Date updatedAt;
    private boolean status;
    private String contactNumber;

}
