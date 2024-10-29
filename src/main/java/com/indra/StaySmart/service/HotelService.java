package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

        hotel.setHotelId(hotelRequestDto.getHotelRequestId() != null ? hotelRequestDto.getHotelRequestId() : UUID.randomUUID());
        hotel.setHotelName(hotelRequestDto.getHotelName());
        hotel.setAddress(hotelRequestDto.getHotelAddress());
        hotel.setContactNumber(hotelRequestDto.getContactNumber());
        hotel.setStatus(hotelRequestDto.isStatus());

        hotel.setCreatedAt(new Date());
        hotel.setUpdatedAt(new Date());


        return hotel;
    }


    private HotelResponseDto convertEntityToDto(Hotel hotel) {
        HotelResponseDto hotelResponseDto = new HotelResponseDto();

        hotelResponseDto.setHotelRequestId(hotel.getHotelId());
        hotelResponseDto.setHotelAddress(hotel.getAddress());
        hotelResponseDto.setHotelName(hotel.getHotelName());
        hotelResponseDto.setContactNumber(hotel.getContactNumber());
        hotelResponseDto.setStatus(hotel.isStatus());

        hotelResponseDto.setCreatedAt(hotel.getCreatedAt());
        hotelResponseDto.setUpdatedAt(hotel.getUpdatedAt());

        return hotelResponseDto;
    }

    public List<HotelResponseDto> getAllHotels() {
        List<HotelResponseDto> ans = new ArrayList<>();
        List<Hotel> hotels = hotelRepository.findAll();
        HotelResponseDto hotelResponseDto = new HotelResponseDto();

        for(Hotel hotel : hotels) {
            hotelResponseDto.setHotelRequestId(hotel.getHotelId());
            hotelResponseDto.setHotelName(hotel.getHotelName());
            hotelResponseDto.setHotelAddress(hotel.getAddress());
            hotelResponseDto.setContactNumber(hotel.getContactNumber());
            hotelResponseDto.setStatus(hotel.isStatus());
            hotelResponseDto.setCreatedAt(hotel.getCreatedAt());
            hotelResponseDto.setUpdatedAt(hotel.getUpdatedAt());
            ans.add(hotelResponseDto);
        }
        return ans;
    }

    public HotelResponseDto getHotelById(UUID hotelId) {
        Hotel hotel = hotelRepository.findByHotelId(hotelId);
        return convertEntityToDto(hotel);
    }
}
