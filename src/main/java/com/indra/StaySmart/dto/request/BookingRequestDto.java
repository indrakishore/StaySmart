package com.indra.StaySmart.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public class BookingRequestDto {

    private UUID hotelId;

    private UUID roomId;

    private LocalDate checkin;

    private LocalDate checkout;

    private UUID customerId;

    private Integer numberOfGuest;

    private Double bookingAmount;
}
