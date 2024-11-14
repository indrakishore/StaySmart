package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.HotelNotFoundException;
import com.indra.StaySmart.customException.ResourceNotFoundException;
import com.indra.StaySmart.dto.request.RoomTypeRequestDto;
import com.indra.StaySmart.dto.response.RoomTypeResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.HotelRoomMappingId;
import com.indra.StaySmart.entity.HotelRoomMappings;
import com.indra.StaySmart.entity.RoomTypeEntity;
import com.indra.StaySmart.repository.HotelRepository;
import com.indra.StaySmart.repository.HotelRoomMappingsRepo;
import com.indra.StaySmart.repository.RoomTypeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RoomTypeService {

    private static final Logger logger = LoggerFactory.getLogger(RoomTypeService.class);

    private final RoomTypeRepository roomTypeRepository;
    private final HotelRepository hotelRepository;
    private final HotelRoomMappingsRepo hotelRoomMappingsRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository, HotelRepository hotelRepository, HotelRoomMappingsRepo hotelRoomMappingsRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.hotelRepository = hotelRepository;
        this.hotelRoomMappingsRepository = hotelRoomMappingsRepository;
    }

    @Transactional
    public RoomTypeResponseDto addRoom(RoomTypeRequestDto roomTypeRequestDto) throws HotelNotFoundException {
        // Fetch the Hotel object using the provided hotelId. Throw exception if not found.
        Hotel hotel = hotelRepository.findById(roomTypeRequestDto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + roomTypeRequestDto.getHotelId()));

        // Convert the incoming DTO to an Entity.
        RoomTypeEntity roomTypeEntity = convertDtoToEntity(roomTypeRequestDto);

        // Save the RoomTypeEntity to the roomTypeRepository.
        roomTypeRepository.save(roomTypeEntity);

        // Save the updated Hotel entity.
        hotelRepository.save(hotel);

        // Create a new HotelRoomMappings object.
        HotelRoomMappings hotelRoomMappings = new HotelRoomMappings();

        // Set the composite ID consisting of hotelId and roomId.
        hotelRoomMappings.setId(new HotelRoomMappingId(hotel.getHotelId(), roomTypeEntity.getRoomId()));
        // Set the Hotel object.
        hotelRoomMappings.setHotel(hotel);
        // Set the RoomTypeEntity object.
        hotelRoomMappings.setRoomTypeEntity(roomTypeEntity);
        // Set the total number of rooms.
        hotelRoomMappings.setTotalRooms(roomTypeRequestDto.getTotalRooms());

        // Save the HotelRoomMappings object to the hotelRoomMappingsRepository.
        hotelRoomMappingsRepository.save(hotelRoomMappings);

        // Convert the saved RoomTypeEntity to a Response DTO and return it.
        return convertEntityToResponseDto(roomTypeEntity);
    }

    @Transactional
    public boolean updateTotalRooms(UUID hotelId, UUID roomId, Integer totalRooms) throws ResourceNotFoundException, HotelNotFoundException {
        // Validate totalRooms
        if (totalRooms < 0) {
            throw new IllegalArgumentException("Total rooms cannot be negative");
        }

        // Fetch the Hotel object using the provided hotelId. Throw exception if not found.
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + hotelId));

        // Fetch the HotelRoomMappings object using the provided hotelId and roomId. Throw exception if not found.
        HotelRoomMappings hotelRoomMappings = hotelRoomMappingsRepository
                .findByHotelIdAndRoomTypeId(hotelId, roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found with Hotel ID: " + hotelId + " and Room ID: " + roomId));

        // Update the total number of rooms.
        hotelRoomMappings.setTotalRooms(totalRooms);

        // Save the updated HotelRoomMappings object to the hotelRoomMappingsRepository.
        hotelRoomMappingsRepository.save(hotelRoomMappings);

        logger.info("Total rooms for Hotel ID: {} and Room ID: {} updated to {}", hotelId, roomId, totalRooms);

        return true;
    }

    private RoomTypeEntity convertDtoToEntity(RoomTypeRequestDto roomTypeRequestDto) {
        return RoomTypeEntity.builder()
                .roomId(roomTypeRequestDto.getRoomId())
                .roomCategory(roomTypeRequestDto.getRoomCategory())
                .amenities(roomTypeRequestDto.getAmenities())
                .maxOccupancy(roomTypeRequestDto.getMaxOccupancy())
                .active(true)
                .createdAt(LocalDate.now())
                .build();
    }

    private RoomTypeResponseDto convertEntityToResponseDto(RoomTypeEntity roomTypeEntity) {
        return RoomTypeResponseDto.builder()
                .roomId(roomTypeEntity.getRoomId())
                .roomCategory(roomTypeEntity.getRoomCategory())
                .amenities(roomTypeEntity.getAmenities())
                .build();
    }

    public RoomTypeResponseDto findRoomByRoomId(UUID roomId) throws ResourceNotFoundException {
        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        return convertEntityToResponseDto(roomTypeEntity);
    }

    public RoomTypeResponseDto updateRoom(UUID roomId, RoomTypeRequestDto roomTypeRequestDto) throws ResourceNotFoundException {
        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        roomTypeEntity.setRoomCategory(roomTypeRequestDto.getRoomCategory());
        roomTypeEntity.setAmenities(roomTypeRequestDto.getAmenities());
        roomTypeEntity.setMaxOccupancy(roomTypeRequestDto.getMaxOccupancy());
        roomTypeEntity.setUpdatedAt(LocalDate.now());

        roomTypeRepository.save(roomTypeEntity);
        return convertEntityToResponseDto(roomTypeEntity);
    }

    public void deleteRoom(UUID roomId) throws ResourceNotFoundException {
        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        roomTypeRepository.delete(roomTypeEntity);
    }
}
