package com.indra.StaySmart.controller;

import com.indra.StaySmart.customException.HotelNotFoundException;
import com.indra.StaySmart.customException.ResourceNotFoundException;
import com.indra.StaySmart.dto.request.RoomTypeRequestDto;
import com.indra.StaySmart.dto.request.UpdateTotalRoomsRequestDto;
import com.indra.StaySmart.dto.response.RoomTypeResponseDto;
import com.indra.StaySmart.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    // Create a new Room
    @PostMapping("/create")
    public ResponseEntity<RoomTypeResponseDto> addRoom(@RequestBody RoomTypeRequestDto roomTypeRequestDto) throws ResourceNotFoundException, HotelNotFoundException {
        RoomTypeResponseDto roomTypeResponseDto = roomTypeService.addRoom(roomTypeRequestDto);
        return new ResponseEntity<>(roomTypeResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/update/total-rooms")
    public boolean updateTotalRooms(@RequestBody UpdateTotalRoomsRequestDto updateTotalRoomsRequestDto) throws ResourceNotFoundException, HotelNotFoundException {
        UUID hotelId = updateTotalRoomsRequestDto.getHotelId();
        UUID roomId = updateTotalRoomsRequestDto.getRoomId();
        Integer totalRooms = updateTotalRoomsRequestDto.getTotalRooms();

        return roomTypeService.updateTotalRooms(hotelId, roomId, totalRooms);
    }

    // Get a Room by ID
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomTypeResponseDto> getRoomById(@PathVariable UUID roomId) throws ResourceNotFoundException {
        RoomTypeResponseDto roomTypeResponseDto = roomTypeService.findRoomByRoomId(roomId);
        return ResponseEntity.ok(roomTypeResponseDto);
    }

    // Update Room Information
    @PutMapping("/{roomId}")
    public ResponseEntity<RoomTypeResponseDto> updateRoom(@PathVariable UUID roomId, @RequestBody RoomTypeRequestDto roomTypeRequestDto) throws ResourceNotFoundException {
        RoomTypeResponseDto updatedRoom = roomTypeService.updateRoom(roomId, roomTypeRequestDto);
        return ResponseEntity.ok(updatedRoom);
    }

    // Delete a Room by ID
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID roomId) throws ResourceNotFoundException {
        roomTypeService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
