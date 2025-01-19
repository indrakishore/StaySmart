//package com.indra.StaySmart.service;
//
//import com.indra.StaySmart.entity.Hotel;
//import com.indra.StaySmart.entity.HotelRoomMappingId;
//import com.indra.StaySmart.entity.HotelRoomMappings;
//import com.indra.StaySmart.entity.RoomTypeEntity;
//import com.indra.StaySmart.repository.HotelRepository;
//import com.indra.StaySmart.repository.HotelRoomMappingsRepo;
//import com.indra.StaySmart.repository.RoomTypeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class HotelRoomMappingServiceTest {
//
//    @Mock
//    private HotelRoomMappingsRepo hotelRoomMappingsRepository;
//
//    @Mock
//    private HotelRepository hotelRepository;
//
//    @Mock
//    private RoomTypeRepository roomTypeRepository;
//
//    @InjectMocks
//    private HotelRoomMappingService hotelRoomMappingService;
//
//    @Test
//    void testCreateHotelRoomMapping_HotelNotFound() {
//        UUID hotelId = UUID.randomUUID();
//        UUID roomId = UUID.randomUUID();
//        Integer totalRooms = 5;
//
//        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () ->
//                hotelRoomMappingService.createHotelRoomMapping(hotelId, roomId, totalRooms));
//
//        verify(hotelRepository, times(1)).findById(hotelId);
//        verifyNoInteractions(roomTypeRepository, hotelRoomMappingsRepository);
//    }
//
//    @Test
//    void testCreateHotelRoomMapping_RoomTypeNotFound() {
//        UUID hotelId = UUID.randomUUID();
//        UUID roomId = UUID.randomUUID();
//        Integer totalRooms = 5;
//
//        Hotel hotel = new Hotel();
//        hotel.setHotelId(hotelId);
//
//        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
//        when(roomTypeRepository.findById(roomId)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () ->
//                hotelRoomMappingService.createHotelRoomMapping(hotelId, roomId, totalRooms));
//
//        verify(hotelRepository, times(1)).findById(hotelId);
//        verify(roomTypeRepository, times(1)).findById(roomId);
//        verifyNoInteractions(hotelRoomMappingsRepository);
//    }
//
//    @Test
//    void testCreateHotelRoomMapping_Success() {
//        UUID hotelId = UUID.randomUUID();
//        UUID roomId = UUID.randomUUID();
//        Integer totalRooms = 5;
//
//        Hotel hotel = new Hotel();
//        hotel.setHotelId(hotelId);
//
//        RoomTypeEntity roomTypeEntity = new RoomTypeEntity();
//        roomTypeEntity.setRoomId(roomId);
//
//        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
//        when(roomTypeRepository.findById(roomId)).thenReturn(Optional.of(roomTypeEntity));
//
//        hotelRoomMappingService.createHotelRoomMapping(hotelId, roomId, totalRooms);
//
//        HotelRoomMappingId mappingId = new HotelRoomMappingId(hotelId, roomId);
//        HotelRoomMappings hotelRoomMappings = new HotelRoomMappings();
//        hotelRoomMappings.setId(mappingId);
//        hotelRoomMappings.setHotel(hotel);
//        hotelRoomMappings.setRoomTypeEntity(roomTypeEntity);
//        hotelRoomMappings.setTotalRooms(totalRooms);
//
//        verify(hotelRoomMappingsRepository, times(1)).save(any(HotelRoomMappings.class));
//    }
//}