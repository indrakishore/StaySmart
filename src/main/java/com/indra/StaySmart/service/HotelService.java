package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.HotelNotFoundException;
import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.request.HotelRoomMappingDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.HotelRoomMappingId;
import com.indra.StaySmart.entity.HotelRoomMappings;
import com.indra.StaySmart.entity.RoomTypeEntity;
import com.indra.StaySmart.repository.HotelRepository;
import com.indra.StaySmart.repository.HotelRoomMappingsRepo;
import com.indra.StaySmart.repository.RoomTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private HotelRoomMappingsRepo hotelRoomMappingsRepository;

    @Transactional
    public HotelResponseDto addHotel(HotelRequestDto hotelRequestDto) {
        // Create the hotel entity
        Hotel hotel = convertDtoToEntity(hotelRequestDto);
        hotelRepository.save(hotel);

        // If there are room mappings, iterate over them and save to hotel_room_mappings
        if (hotelRequestDto.getRoomMappings() != null && !hotelRequestDto.getRoomMappings().isEmpty()) {
            saveRoomMappings(hotelRequestDto.getRoomMappings(), hotel);
        }

        // Convert and return the hotel response DTO
        return convertEntityToDto(hotel);
    }

    private void saveRoomMappings(List<HotelRoomMappingDto> roomMappings, Hotel hotel) {
        for (HotelRoomMappingDto mappingDto : roomMappings) {
            // Fetch the room type by its ID
            RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(mappingDto.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));

            // Create the composite key using hotelId and roomId
            HotelRoomMappingId mappingId = new HotelRoomMappingId(hotel.getHotelId(), roomTypeEntity.getRoomId());

            // Create a new HotelRoomMappings entity
            HotelRoomMappings mapping = new HotelRoomMappings();
            mapping.setId(mappingId);  // Set the composite key
            mapping.setHotel(hotel);    // Set the hotel
            mapping.setRoomTypeEntity(roomTypeEntity);  // Set the room type
            mapping.setTotalRooms(mappingDto.getTotalRooms());  // Set the number of rooms

            // Save the room mapping
            hotelRoomMappingsRepository.save(mapping);
        }
    }


    private Hotel convertDtoToEntity(HotelRequestDto hotelRequestDto) {
        return Hotel.builder()
                .hotelId(hotelRequestDto.getHotelId())
                .hotelName(hotelRequestDto.getHotelName())
                .address(hotelRequestDto.getHotelAddress())
                .city(hotelRequestDto.getCity())
                .contactNumber(hotelRequestDto.getContactNumber())
                .status(hotelRequestDto.getStatus())
                .rating(hotelRequestDto.getRating())
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();
    }

    private HotelResponseDto convertEntityToDto(Hotel hotel) {
        return HotelResponseDto.builder()
                .hotelId(hotel.getHotelId())
                .hotelName(hotel.getHotelName())
                .hotelAddress(hotel.getAddress())
                .city(hotel.getCity())
                .status(hotel.getStatus())
                .rooms(hotel.getRoomTypeEntityList())
                .rating(hotel.getRating())
                .contactNumber(hotel.getContactNumber())
                .build();
    }

    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelResponseDto> hotelResponseDtos = new ArrayList<>();

        for (Hotel hotel : hotels) {
            hotelResponseDtos.add(convertEntityToDto(hotel));
        }

        return hotelResponseDtos;
    }

    public HotelResponseDto getHotelById(UUID hotelId) throws HotelNotFoundException {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found with this hotel ID: " + hotelId));

        return convertEntityToDto(hotel);
    }

@Transactional
public HotelResponseDto updateHotel(UUID id, HotelRequestDto updateHotelDto) throws HotelNotFoundException {
    Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with this hotel ID: " + id));

    hotel.setHotelName(updateHotelDto.getHotelName());
    hotel.setAddress(updateHotelDto.getHotelAddress());
    hotel.setContactNumber(updateHotelDto.getContactNumber());
    hotel.setStatus(updateHotelDto.getStatus());
    hotel.setRating(updateHotelDto.getRating());
    hotel.setUpdatedAt(LocalDate.now());

    hotelRepository.save(hotel);

    // Optionally, update room mappings
    if (updateHotelDto.getRoomMappings() != null && !updateHotelDto.getRoomMappings().isEmpty()) {
        hotelRoomMappingsRepository.deleteByHotelId(hotel.getHotelId());
        saveRoomMappings(updateHotelDto.getRoomMappings(), hotel);
    }

    return convertEntityToDto(hotel);
}

@Transactional
public HotelResponseDto updateHotelByPatch(UUID id, HotelRequestDto hotelRequestDto) throws HotelNotFoundException {
    Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new HotelNotFoundException("Hotel not found with this hotel ID: " + id));

    if (hotelRequestDto.getHotelName() != null) {
        hotel.setHotelName(hotelRequestDto.getHotelName());
    }
    if (hotelRequestDto.getHotelAddress() != null) {
        hotel.setAddress(hotelRequestDto.getHotelAddress());
    }
    if (hotelRequestDto.getContactNumber() != null) {
        hotel.setContactNumber(hotelRequestDto.getContactNumber());
    }
    if (hotelRequestDto.getStatus() != null) {
        hotel.setStatus(hotelRequestDto.getStatus());
    }
    if (hotelRequestDto.getRating() != null) {
        hotel.setRating(hotelRequestDto.getRating());
    }
    hotel.setUpdatedAt(LocalDate.now());

    hotelRepository.save(hotel);

    return convertEntityToDto(hotel);
}

@Transactional
public void deleteHotel(UUID hotelId) throws HotelNotFoundException {
    if (!hotelRepository.existsById(hotelId)) {
        throw new HotelNotFoundException("Hotel not found with this hotel ID: " + hotelId);
    }
    hotelRoomMappingsRepository.deleteByHotelId(hotelId);
    hotelRepository.deleteById(hotelId);
}

    public List<HotelResponseDto> getHotelsByCity(String city) {
        List<Hotel> hotels = hotelRepository.findByCity(city);
        List<HotelResponseDto> hotelResponseDtos = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelResponseDtos.add(convertEntityToDto(hotel));
        }
        return hotelResponseDtos;
    }
}
