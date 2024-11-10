package com.indra.StaySmart.controller;

import com.indra.StaySmart.customException.ResourceNotFoundException;
import com.indra.StaySmart.dto.request.RoomRequestDto;
import com.indra.StaySmart.dto.response.RoomResponseDto;
import com.indra.StaySmart.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @PostMapping("/create")
    public RoomResponseDto addRoom(@RequestBody RoomRequestDto roomRequestDto) throws ResourceNotFoundException {
        return roomService.addRoom(roomRequestDto);
    }

//    @GetMapping()
//    public ResponseEntity<RoomResponseDto> getRoomById(@RequestParam("roomId") UUID roomId) {
//        RoomResponseDto roomResponseDto = roomService.findRoomByRoomId(roomId);
//        return ResponseEntity.ok(roomResponseDto);
//    }

    //todo:
    /***
     * Update Room Information:
     *
     * Endpoint: PUT /api/v1/rooms/{roomId}
     * Description: Update the details of a room.
     */

    /***
     * Delete Room:
     *
     * Endpoint: DELETE /api/v1/rooms/{roomId}
     * Description: Delete a room by room ID.
     */

}
