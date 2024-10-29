package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.RoomRequestDto;
import com.indra.StaySmart.dto.response.RoomResponseDto;
import com.indra.StaySmart.entity.Room;
import com.indra.StaySmart.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @PostMapping
    public RoomResponseDto addRoom(@RequestBody RoomRequestDto roomRequestDto) {
        return roomService.addRoom(roomRequestDto);
    }

    @GetMapping()
    public ResponseEntity<RoomResponseDto> getRoomById(@RequestParam("roomId") UUID roomId) {
        RoomResponseDto roomResponseDto = roomService.findRoomById(roomId);
        return ResponseEntity.ok(roomResponseDto);
    }




}
