package com.indra.StaySmart.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class InventoryPricingDto {
    @NotNull(message = "Hotel ID is required")
    private UUID hotelId;

    @NotNull(message = "Room ID is required")
    private UUID roomId;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive value")
    private Double price;

    @NotNull(message = "Inventory is required")
    @Positive(message = "Inventory must be a positive integer")
    private Integer inventory;
}

