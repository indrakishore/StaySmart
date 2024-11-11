package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.PriceInventoryDto;
import com.indra.StaySmart.dto.response.PriceInventoryResponseDto;
import com.indra.StaySmart.service.PriceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pricing-inventory/")
public class PriceInventoryController {

    @Autowired
    PriceInventoryService priceInventoryService;

    @PostMapping("/add")
    public ResponseEntity<PriceInventoryResponseDto> add(@RequestBody PriceInventoryDto priceInventoryDto) {
        PriceInventoryResponseDto response = priceInventoryService.priceInventory(priceInventoryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


//    public List<PriceInventoryResponseDto> getPriceAndInvetoryForHotel(@RequestParam("hotelId") Integer hotelId, @RequestParam("checkin") LocalDate checkin) {
//        return priceInventoryService.getPriceAndInvetoryForHotel(hotelId, checkin);
//    }
}
