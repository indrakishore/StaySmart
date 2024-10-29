package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/create")
    private HotelResponseDto createHotel(@RequestBody HotelRequestDto hotelRequestDto) {
        return hotelService.addHotel(hotelRequestDto);
    }

    @GetMapping
    public HotelResponseDto getHotel(@RequestParam("hotelId") UUID hotelId) {
        return hotelService.getHotelById(hotelId);
    }


    @GetMapping("/getAllHotels")
    private List<HotelResponseDto> getAllHotels() {
        return hotelService.getAllHotels();
    }

}
