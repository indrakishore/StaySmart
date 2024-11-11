package com.indra.StaySmart.dto.response;

import com.indra.StaySmart.entity.Booking;
import com.indra.StaySmart.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {

    private UUID bookingId;
    private UUID customerId;
    private UUID inventoryId;
    private BookingStatus status;

    public BookingResponseDto(Booking booking) {
    }
}
