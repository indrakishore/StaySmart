package com.indra.StaySmart.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PriceInventoryResponseDto {


    private UUID hotelId;


    private UUID roomId;


    private Boolean isSoldOut;


    private Double price;


    private LocalDate date;

}