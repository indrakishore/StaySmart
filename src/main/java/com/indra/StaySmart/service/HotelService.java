package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public HotelResponseDto addHotel(HotelRequestDto hotelRequestDto) {
        Hotel hotel = convertDtoToEntity(hotelRequestDto);
        hotelRepository.save(hotel);
        return convertEntityToDto(hotel);
    }

    private Hotel convertDtoToEntity(HotelRequestDto hotelRequestDto) {
        Hotel hotel = new Hotel();

        hotel.setHotelId(hotelRequestDto.getHotelId() != null ? hotelRequestDto.getHotelId() : UUID.randomUUID());
        hotel.setHotelName(hotelRequestDto.getHotelName());
        hotel.setAddress(hotelRequestDto.getHotelAddress());
        hotel.setContactNumber(hotelRequestDto.getContactNumber());
        hotel.setStatus(hotelRequestDto.getStatus());

        hotel.setCreatedAt(LocalDate.now()); // Use LocalDate
        hotel.setUpdatedAt(LocalDate.now()); // Use LocalDate

        return hotel;
    }

    private HotelResponseDto convertEntityToDto(Hotel hotel) {
        return new HotelResponseDto(
                hotel.getHotelId(),
                hotel.getHotelName(),
                hotel.getAddress(),
                hotel.getCreatedAt(),
                hotel.getUpdatedAt(),
                hotel.getStatus(),
                hotel.getContactNumber()
        );
    }

    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelResponseDto> hotelResponseDtos = new ArrayList<>();

        for (Hotel hotel : hotels) {
            hotelResponseDtos.add(convertEntityToDto(hotel)); // Use the method to convert entity to DTO
        }

        return hotelResponseDtos;
    }

    public HotelResponseDto getHotelById(UUID hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        return convertEntityToDto(hotel);
    }
}
