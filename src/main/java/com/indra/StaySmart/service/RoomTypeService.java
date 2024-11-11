package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.HotelNotFoundException;
import com.indra.StaySmart.customException.ResourceNotFoundException;
import com.indra.StaySmart.dto.request.RoomTypeRequestDto;
import com.indra.StaySmart.dto.response.RoomTypeResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.RoomTypeEntity;
import com.indra.StaySmart.repository.HotelRepository;
import com.indra.StaySmart.repository.RoomTypeRepository;
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

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository, HotelRepository hotelRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.hotelRepository = hotelRepository;
    }

    public RoomTypeResponseDto addRoom(RoomTypeRequestDto roomTypeRequestDto) throws HotelNotFoundException {
        Hotel hotel = hotelRepository.findById(roomTypeRequestDto.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with ID: " + roomTypeRequestDto.getHotelId()));

        RoomTypeEntity roomTypeEntity = convertDtoToEntity(roomTypeRequestDto);
        roomTypeRepository.save(roomTypeEntity);
        hotelRepository.save(hotel);

        return convertEntityToResponseDto(roomTypeEntity);
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
