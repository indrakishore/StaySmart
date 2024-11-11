package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.HotelNotFoundException;
import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.repository.HotelRepository;
import com.indra.StaySmart.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    public HotelResponseDto addHotel(HotelRequestDto hotelRequestDto) {
        Hotel hotel = convertDtoToEntity(hotelRequestDto);
        hotelRepository.save(hotel);
        return convertEntityToDto(hotel);
    }

    private Hotel convertDtoToEntity(HotelRequestDto hotelRequestDto) {
        Hotel hotel = new Hotel();

        hotel.setHotelId(hotelRequestDto.getHotelId());
        hotel.setHotelName(hotelRequestDto.getHotelName());
        hotel.setAddress(hotelRequestDto.getHotelAddress());
        hotel.setContactNumber(hotelRequestDto.getContactNumber());
        hotel.setStatus(hotelRequestDto.getStatus());
        hotel.setRating(hotelRequestDto.getRating());

        hotel.setCreatedAt(LocalDate.now()); // Use LocalDate
        hotel.setUpdatedAt(LocalDate.now()); // Use LocalDate

        return hotel;
    }

    private HotelResponseDto convertEntityToDto(Hotel hotel) {
        HotelResponseDto responseDto = new HotelResponseDto();

        responseDto.setHotelId(hotel.getHotelId());
        responseDto.setHotelName(hotel.getHotelName());
        responseDto.setHotelAddress(hotel.getAddress());
        responseDto.setRooms(hotel.getRoomList());
//        responseDto.setCreatedAt(hotel.getCreatedAt());
//        responseDto.setUpdatedAt(hotel.getUpdatedAt());
//        responseDto.setStatus(hotel.getStatus());
        responseDto.setRating(hotel.getRating());
        responseDto.setContactNumber(hotel.getContactNumber());

        return responseDto;
    }

    //Using builder
//    private HotelResponseDto convertHotelToResponseDto(Hotel hotel) {
//        return HotelResponseDto.builder().hotelName(hotel.getHotelName())
//                .address(hotel.getAddress()).rooms(hotel.getRoomList()).build();
////    HotelResponseDto hotelResponseDto = new HotelResponseDto();
////    hotelResponseDto.setHotelName(hotel.getHotelName());
////    hotelResponseDto.setAddress(hotel.getAddress());
////    hotelResponseDto.setRooms(hotel.getRoomList());
//
//        // more details to set if you want that you share to your client
//    }

    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelResponseDto> hotelResponseDtos = new ArrayList<>();

        for (Hotel hotel : hotels) {
            hotelResponseDtos.add(convertEntityToDto(hotel)); // Use the method to convert entity to DTO
        }

        return hotelResponseDtos;
    }

    public HotelResponseDto getHotelById(UUID hotelId) throws HotelNotFoundException {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty()) {
            throw new HotelNotFoundException("Hotel not found with this hotelid : " + hotelId);
        }
        return convertEntityToDto(hotel.orElse(null));
    }

//    public HotelResponseDto updateHotel(UUID id, HotelRequestDto updateHotelDto) {
//        Hotel hotel = hotelRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Hotel not Found"));
//
//        hotel.setHotelName(updateHotelDto.getHotelName());
//        hotel.setAddress(updateHotelDto.getHotelAddress());
//        hotel.setContactNumber(updateHotelDto.getContactNumber());
//        hotel.setStatus(updateHotelDto.getStatus());
//
//        if (updateHotelDto.getRating() != null) { // Check for rating in update
//            hotel.setRating(updateHotelDto.getRating());
//        }
//
//        hotel.setUpdatedAt(LocalDate.now());
//
//        Hotel updatedHotel = hotelRepository.save(hotel);
//        return convertEntityToDto(updatedHotel);
//    }

//    public HotelResponseDto updateHotelByPatch(UUID id, HotelRequestDto hotelRequestDto) {
//        Hotel hotel = hotelRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Hotel not found"));
//
//        // Update only non-null fields
//        if (hotelRequestDto.getHotelName() != null) {
//            hotel.setHotelName(hotelRequestDto.getHotelName());
//        }
//        if (hotelRequestDto.getHotelAddress() != null) {
//            hotel.setAddress(hotelRequestDto.getHotelAddress());
//        }
//        if (hotelRequestDto.getContactNumber() != null) {
//            hotel.setContactNumber(hotelRequestDto.getContactNumber());
//        }
//        if (hotelRequestDto.getStatus() != null) {
//            hotel.setStatus(hotelRequestDto.getStatus());
//        }
//        if (hotelRequestDto.getRating() != null) { // Check for rating in patch update
//            hotel.setRating(hotelRequestDto.getRating());
//        }
//
//        hotel.setUpdatedAt(LocalDate.now());
//
//        Hotel updatedHotel = hotelRepository.save(hotel);
//
//        // Convert to DTO and return
//        return convertEntityToDto(updatedHotel);
//    }

//    @Transactional
//    public String deleteHotel(UUID hotelId) {
//        // Fetch the hotel
//        Hotel hotel = hotelRepository.findById(hotelId)
//                .orElseThrow(() -> new RuntimeException("Hotel not found"));
//
//        // Delete all rooms associated with this hotel
////        roomRepository.deleteAllByHotel(hotel);
//
//        // Now delete the hotel
//        hotelRepository.delete(hotel);
//
//        return "Hotel deleted successfully along with associated rooms.";
//    }

//    private HotelResponseDto convertToResponseDto(Hotel updatedHotel) {
//
//        HotelResponseDto hotelResponseDto = new HotelResponseDto();
//
//        hotelResponseDto.setHotelId(updatedHotel.getHotelId());
//        hotelResponseDto.setHotelName(updatedHotel.getHotelName());
//        hotelResponseDto.setHotelAddress(updatedHotel.getAddress());
//        hotelResponseDto.setContactNumber(updatedHotel.getContactNumber());
//        hotelResponseDto.setStatus(updatedHotel.getStatus());
//        hotelResponseDto.setUpdatedAt(updatedHotel.getUpdatedAt());
//
//        return hotelResponseDto;
//    }





}

