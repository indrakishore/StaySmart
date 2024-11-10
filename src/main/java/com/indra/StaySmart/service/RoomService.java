package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.ResourceNotFoundException;
import com.indra.StaySmart.dto.request.RoomRequestDto;
import com.indra.StaySmart.dto.response.RoomResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.HotelRoomMappings;
import com.indra.StaySmart.entity.Room;
import com.indra.StaySmart.repository.HotelRepository;
import com.indra.StaySmart.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    // Add a new Room to a Hotel
    public RoomResponseDto addRoom(RoomRequestDto roomRequestDto) throws ResourceNotFoundException {
        // Find the hotel by ID
        Hotel hotel = hotelRepository.findById(roomRequestDto.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + roomRequestDto.getHotelId()));

        // Create and save the room
        Room room = convertDtoToEntity(roomRequestDto);
        roomRepository.save(room);

        // Create HotelRoomMappings object and set the total rooms
        HotelRoomMappings mapping = new HotelRoomMappings();
        mapping.setHotel(hotel);
        mapping.setRoom(room);

        // Ensure totalRooms is set correctly
        if (roomRequestDto.getTotalRooms() != null && roomRequestDto.getTotalRooms() > 0) {
            mapping.setTotalRooms(roomRequestDto.getTotalRooms());
        } else {
            logger.error("Total rooms not provided or invalid: " + roomRequestDto.getTotalRooms());
            throw new IllegalArgumentException("Total rooms must be provided and greater than zero");
        }

        // Log the mapping details to verify the correct values
        logger.info("Creating HotelRoomMapping: Hotel ID = {}, Room ID = {}, Total Rooms = {}",
                hotel.getHotelId(), room.getRoomId(), mapping.getTotalRooms());

        // Add mapping to the hotel and room
        hotel.getHotelRoomMappings().add(mapping);
        room.getHotelRoomMappings().add(mapping);

        // Save the updated hotel entity with the new room and mapping
        hotelRepository.save(hotel);

        // Return the response DTO for the created room
        return convertEntityToResponseDto(room);
    }


    // Convert RoomRequestDto to Room entity
    private Room convertDtoToEntity(RoomRequestDto roomRequestDto) {
        Room room = new Room();
        room.setRoomId(roomRequestDto.getRoomId());
        room.setRoomName(roomRequestDto.getRoomName());
        room.setRoomType(roomRequestDto.getRoomType());
        room.setAmenities(roomRequestDto.getAmenities());
        room.setMaxOccupancy(roomRequestDto.getMaxOccupancy());
        room.setActive(true);
        room.setCreatedAt(LocalDate.now());
        room.setUpdatedAt(LocalDate.now());
        return room;
    }

    // Convert Room entity to RoomResponseDto
    // Convert Room entity to RoomResponseDto
    private RoomResponseDto convertEntityToResponseDto(Room room) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setRoomId(room.getRoomId());
        roomResponseDto.setRoomName(room.getRoomName());
        roomResponseDto.setAmenities(room.getAmenities());
        roomResponseDto.setRoomType(room.getRoomType());

        // Fetch the hotelId from the HotelRoomMappings list
        if (room.getHotelRoomMappings() != null && !room.getHotelRoomMappings().isEmpty()) {
            roomResponseDto.setHotelId(room.getHotelRoomMappings().get(0).getHotel().getHotelId());
        } else {
            roomResponseDto.setHotelId(null); // Set null if no mappings found
        }

        return roomResponseDto;
    }


    // Find a Room by ID
    public RoomResponseDto findRoomByRoomId(UUID roomId) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        return convertEntityToResponseDto(room);
    }

    // Update Room Information
    public RoomResponseDto updateRoom(UUID roomId, RoomRequestDto roomRequestDto) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        room.setRoomName(roomRequestDto.getRoomName());
        room.setRoomType(roomRequestDto.getRoomType());
        room.setAmenities(roomRequestDto.getAmenities());
        room.setMaxOccupancy(roomRequestDto.getMaxOccupancy());
        room.setUpdatedAt(LocalDate.now());

        roomRepository.save(room);
        return convertEntityToResponseDto(room);
    }

    // Delete a Room by ID
    public void deleteRoom(UUID roomId) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        roomRepository.delete(room);
    }
}
