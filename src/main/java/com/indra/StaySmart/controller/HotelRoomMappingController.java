package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.HotelRoomMappingDto;
import com.indra.StaySmart.service.HotelRoomMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hotel-room-mappings")
public class HotelRoomMappingController {

    @Autowired
    private HotelRoomMappingService hotelRoomMappingService;

    @PostMapping("/add")
    public ResponseEntity<Void> createMapping(@RequestBody HotelRoomMappingDto requestDto) {
        hotelRoomMappingService.createHotelRoomMapping(requestDto.getHotelId(), requestDto.getRoomId(), requestDto.getTotalRooms());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

