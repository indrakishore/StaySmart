package com.indra.StaySmart.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceInventoryResponseDto {


    private UUID hotelId;


    private UUID roomId;


    private Boolean isSoldOut;


    private Double price;


    private LocalDate startDate;

    private LocalDate endDate;

}