package com.indra.StaySmart.dto.request;

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
public class InventoryPricingDto {

    private UUID hotelId;
    private UUID roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private Integer inventory;

    // Getters and setters omitted for brevity

}
