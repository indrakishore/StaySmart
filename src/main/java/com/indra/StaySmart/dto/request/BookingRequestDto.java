package com.indra.StaySmart.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BookingRequestDto {
    private UUID bookingId;

    private UUID customerId;

    private UUID inventoryId;

    private UUID hotelId;

    private UUID roomId;

    private LocalDate checkin;

    private LocalDate checkout;

    private Integer numberOfGuest;

    private Double bookingAmount;
}
