package com.indra.StaySmart.service;

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

import java.util.UUID;

@Service
public class HotelRoomMappingService {

    @Autowired
    private HotelRoomMappingsRepo hotelRoomMappingsRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository; // Inject RoomTypeRepository instead of RoomTypeEntity

    @Transactional
    public void createHotelRoomMapping(UUID hotelId, UUID roomId, Integer totalRooms) {
        // 3. Create the hotel-room mapping
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        HotelRoomMappingId mappingId = new HotelRoomMappingId(hotel.getHotelId(), roomTypeEntity.getRoomId());
        HotelRoomMappings hotelRoomMappings = new HotelRoomMappings();
        hotelRoomMappings.setId(mappingId);
        hotelRoomMappings.setHotel(hotel);
        hotelRoomMappings.setRoomTypeEntity(roomTypeEntity);
        hotelRoomMappings.setTotalRooms(totalRooms);  // Set the number of rooms

        hotelRoomMappingsRepository.save(hotelRoomMappings);
    }
}
