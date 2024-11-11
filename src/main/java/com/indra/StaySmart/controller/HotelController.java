package com.indra.StaySmart.controller;

import com.indra.StaySmart.customException.HotelNotFoundException;
import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

//    todo: get all hotels by location


    @PostMapping("create")
    private ResponseEntity<HotelResponseDto> createHotel(@RequestBody HotelRequestDto hotelRequestDto) {
        HotelResponseDto response = hotelService.addHotel(hotelRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity getHotelDetails(@RequestParam("hotelId") UUID hotelId) {

            try {
                HotelResponseDto hotelResponseDto = hotelService.getHotelById(hotelId);
                return ResponseEntity.ok(hotelResponseDto);
            } catch (HotelNotFoundException e) {
                HotelResponseDto hotelResponseDto = new HotelResponseDto();
//                hotelResponseDto.setErrorMessage(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel Not Found");
            }

    }


    @GetMapping("getAllHotels")
    private List<HotelResponseDto> getAllHotels() {

        return hotelService.getAllHotels();
    }

//    @PutMapping("update/{id}")
//    public HotelResponseDto updateHotel(@PathVariable UUID id,@RequestBody HotelRequestDto updateHotelDto) {
//        HotelResponseDto updatedHotelDto = hotelService.updateHotel(id, updateHotelDto);
//        return updatedHotelDto;
//    }

//    @PatchMapping("update/{id}")
//    public ResponseEntity<HotelResponseDto> updateHotelByPatch(@PathVariable UUID id, @RequestBody HotelRequestDto hotelRequestDto) {
//        HotelResponseDto updatedHotelDto = hotelService.updateHotelByPatch(id, hotelRequestDto);
//        return ResponseEntity.ok(updatedHotelDto);
//    }

//    @DeleteMapping("delete/{hotelId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteHotel(@PathVariable UUID hotelId) {
//        hotelService.deleteHotel(hotelId);
//    }


}
