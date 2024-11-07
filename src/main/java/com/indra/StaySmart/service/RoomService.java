package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.ResourceNotFoundException;
import com.indra.StaySmart.dto.request.RoomRequestDto;
import com.indra.StaySmart.dto.response.RoomResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.Room;
import com.indra.StaySmart.repository.HotelRepository;
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

    @Autowired
    HotelRepository hotelRepository;

    public RoomResponseDto addRoom(RoomRequestDto roomRequestDto) throws ResourceNotFoundException {
        //convert DTO to Entity
        Room room = convertDtoToEntity(roomRequestDto);

        // Ensure the hotel is not null
        if (room.getHotel() == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }

        // Check if the room name already exists for this hotel
        Room existingRoom = roomRepository.findByRoomNameAndHotel_HotelId(room.getRoomName(), room.getHotel().getHotelId());
        if (existingRoom != null) {
            throw new IllegalArgumentException("Room with the same name already exists in the hotel.");
        }

        //save the room entity
        room = roomRepository.save(room);
        //return response DTO
        return convertEntityToDto(room);
    }

    private RoomResponseDto convertEntityToDto(Room room) {

        RoomResponseDto roomResponseDto = new RoomResponseDto();

        roomResponseDto.setRoomId(room.getRoomId());
        roomResponseDto.setRoomName(room.getRoomName());
        roomResponseDto.setAmenities(room.getAmenities());
        roomResponseDto.setRoomType(room.getRoomType());
        roomResponseDto.setCreatedAt(room.getCreatedAt());
        roomResponseDto.setUpdatedAt(room.getUpdatedAt());

        // Set hotelId from the associated Hotel object
        if (room.getHotel() != null) {
            roomResponseDto.setHotelId(room.getHotel().getHotelId());
        }

        return roomResponseDto;

    }

    private Room convertDtoToEntity(RoomRequestDto roomRequestDto) throws ResourceNotFoundException {

        Room room = new Room();

        // Retrieve the hotel by its ID
        Hotel hotel = hotelRepository.findById(roomRequestDto.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        room.setHotel(hotel);  // Ensure you set the hotel


        room.setRoomId(roomRequestDto.getRoomId() != null ? roomRequestDto.getRoomId() : UUID.randomUUID());
        room.setRoomName(roomRequestDto.getRoomName());
        room.setRoomType(roomRequestDto.getRoomType());
        room.setAmenities(roomRequestDto.getAmenities());

        // Set current date if createdAt or updatedAt is not provided in the DTO
        room.setCreatedAt(roomRequestDto.getCreatedAt() != null ? roomRequestDto.getCreatedAt() : java.time.LocalDate.now());
        room.setUpdatedAt(roomRequestDto.getUpdatedAt() != null ? roomRequestDto.getUpdatedAt() : java.time.LocalDate.now());



        return room;
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


//    public RoomResponseDto findRoomByRoomId(UUID roomId) {
//        Room room = roomRepository.findByRoomId(roomId);
//
//        if (room == null) {
//            throw new EntityNotFoundException("Room not found with ID: " + roomId);
//        }
//
//        return convertToResponseDto(room);
//    }
}
