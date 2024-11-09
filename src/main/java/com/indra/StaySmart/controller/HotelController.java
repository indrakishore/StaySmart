package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("create")
    private HotelResponseDto createHotel(@RequestBody HotelRequestDto hotelRequestDto) {
        return hotelService.addHotel(hotelRequestDto);
    }

    @GetMapping
    public HotelResponseDto getHotel(@RequestParam("hotelId") UUID hotelId) {
        return hotelService.getHotelById(hotelId);
    }


    @GetMapping("getAllHotels")
    private List<HotelResponseDto> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PutMapping("update/{id}")
    public HotelResponseDto updateHotel(@PathVariable UUID id,@RequestBody HotelRequestDto updateHotelDto) {
        HotelResponseDto updatedHotelDto = hotelService.updateHotel(id, updateHotelDto);
        return updatedHotelDto;
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<HotelResponseDto> updateHotelByPatch(@PathVariable UUID id, @RequestBody HotelRequestDto hotelRequestDto) {
        HotelResponseDto updatedHotelDto = hotelService.updateHotelByPatch(id, hotelRequestDto);
        return ResponseEntity.ok(updatedHotelDto);
    }

    @DeleteMapping("delete/{hotelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHotel(@PathVariable UUID hotelId) {
        hotelService.deleteHotel(hotelId);
    }


}
