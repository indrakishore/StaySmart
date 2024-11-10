package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.InventoryPricingDto;
import com.indra.StaySmart.dto.response.PriceInventoryResponseDto;
import com.indra.StaySmart.service.PriceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/pricing-inventory")
public class PriceInventoryController {

    @Autowired
    PriceInventoryService priceInventoryService;

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody InventoryPricingDto inventoryPricingDto) {
        String response = priceInventoryService.addInventoryPricing(inventoryPricingDto);
        return ResponseEntity.ok(response);
    }


//    public List<PriceInventoryResponseDto> getPriceAndInvetoryForHotel(@RequestParam("hotelId") Integer hotelId, @RequestParam("checkin") LocalDate checkin) {
//        return priceInventoryService.getPriceAndInvetoryForHotel(hotelId, checkin);
//    }
}
