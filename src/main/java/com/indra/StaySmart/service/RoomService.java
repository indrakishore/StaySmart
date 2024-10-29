package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.RoomRequestDto;
import com.indra.StaySmart.dto.response.RoomResponseDto;
import com.indra.StaySmart.entity.Room;
import com.indra.StaySmart.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class RoomService {


    @Autowired
    RoomRepository roomRepository;

    public RoomResponseDto addRoom(RoomRequestDto roomRequestDto) {
        Room room = convertDtoToEntity(roomRequestDto);
        Room savedRoom = roomRepository.save(room);  // Save and retrieve the saved instance
        return convertEntityToDto(savedRoom);
    }

    private RoomResponseDto convertEntityToDto(Room room) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();

        roomResponseDto.setRoomId(room.getRoomId());
        roomResponseDto.setRoomName(room.getRoomName());
        roomResponseDto.setAmenities(room.getAmenities());
        roomResponseDto.setRoomType(room.getRoomType());
        roomResponseDto.setCreatedAt(room.getCreatedAt());
        roomResponseDto.setUpdatedAt(room.getUpdatedAt());

        return roomResponseDto;

    }

    private Room convertDtoToEntity(RoomRequestDto roomRequestDto) {
        Room room = new Room();

        room.setRoomId(roomRequestDto.getRoomId() != null ? roomRequestDto.getRoomId() : UUID.randomUUID());
        room.setRoomName(roomRequestDto.getRoomName());
        room.setRoomType(roomRequestDto.getRoomType());
        room.setAmenities(roomRequestDto.getAmenities());

        // Set current date if createdAt or updatedAt is not provided in the DTO
        room.setCreatedAt(roomRequestDto.getCreatedAt() != null ? roomRequestDto.getCreatedAt() : new Date());
        room.setUpdatedAt(roomRequestDto.getUpdatedAt() != null ? roomRequestDto.getUpdatedAt() : new Date());


        return room;
    }

    public RoomResponseDto findRoomById(UUID roomId) {
        Room room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            throw new EntityNotFoundException("Room not found with ID: " + roomId);
        }

        return convertToResponseDto(room);
    }

    public RoomResponseDto convertToResponseDto(Room room) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();

        // Setting fields from Room entity to RoomResponseDto
        roomResponseDto.setRoomId(room.getRoomId());
        roomResponseDto.setRoomName(room.getRoomName());
        roomResponseDto.setRoomType(room.getRoomType());
        roomResponseDto.setAmenities(room.getAmenities());
        roomResponseDto.setCreatedAt(room.getCreatedAt());
        roomResponseDto.setUpdatedAt(room.getUpdatedAt());

        return roomResponseDto;
    }



}
