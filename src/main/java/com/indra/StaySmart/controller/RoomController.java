package com.indra.StaySmart.controller;

import com.indra.StaySmart.customException.ResourceNotFoundException;
import com.indra.StaySmart.dto.request.RoomRequestDto;
import com.indra.StaySmart.dto.response.RoomResponseDto;
import com.indra.StaySmart.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Create a new Room
    @PostMapping("/create")
    public ResponseEntity<RoomResponseDto> addRoom(@RequestBody RoomRequestDto roomRequestDto) throws ResourceNotFoundException {
        RoomResponseDto roomResponseDto = roomService.addRoom(roomRequestDto);
        return new ResponseEntity<>(roomResponseDto, HttpStatus.CREATED);
    }

    // Get a Room by ID
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponseDto> getRoomById(@PathVariable UUID roomId) throws ResourceNotFoundException {
        RoomResponseDto roomResponseDto = roomService.findRoomByRoomId(roomId);
        return ResponseEntity.ok(roomResponseDto);
    }

    // Update Room Information
    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponseDto> updateRoom(@PathVariable UUID roomId, @RequestBody RoomRequestDto roomRequestDto) throws ResourceNotFoundException {
        RoomResponseDto updatedRoom = roomService.updateRoom(roomId, roomRequestDto);
        return ResponseEntity.ok(updatedRoom);
    }

    // Delete a Room by ID
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID roomId) throws ResourceNotFoundException {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
