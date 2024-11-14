package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.PriceInventoryDto;
import com.indra.StaySmart.dto.response.PriceInventoryResponseDto;
import com.indra.StaySmart.service.PriceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/price-inventory")
public class PriceInventoryController {

    @Autowired
    PriceInventoryService priceInventoryService;

    @PostMapping("/add")
    public ResponseEntity<PriceInventoryResponseDto> add(@RequestBody PriceInventoryDto priceInventoryDto) {
        PriceInventoryResponseDto response = priceInventoryService.priceInventory(priceInventoryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public PriceInventoryResponseDto getInventory(@RequestParam("hotelId") UUID hotelId, @RequestParam("checkin") LocalDate checkin) {
        return priceInventoryService.getInventory(hotelId, checkin);
    }


    public List<PriceInventoryResponseDto> getPriceAndInvetoryForHotel(@RequestParam("hotelId") Integer hotelId, @RequestParam("checkin") LocalDate checkin) {
        return priceInventoryService.getPriceAndInvetoryForHotel(hotelId, checkin);
    }
}
