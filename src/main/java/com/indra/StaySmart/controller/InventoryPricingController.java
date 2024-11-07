package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.InventoryPricingDto;
import com.indra.StaySmart.service.InventoryPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pricing-inventory") // Adds new pricing and inventory information for a specific hotel, room, and date.
public class InventoryPricingController {

    @Autowired
    InventoryPricingService inventoryPricingService;

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody InventoryPricingDto inventoryPricingDto) {
        String response = inventoryPricingService.addInventoryPricing(inventoryPricingDto);
        return ResponseEntity.ok(response);
    }

}
