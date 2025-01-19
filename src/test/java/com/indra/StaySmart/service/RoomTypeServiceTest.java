//package com.indra.StaySmart.service;
//
//import com.indra.StaySmart.customException.HotelNotFoundException;
//import com.indra.StaySmart.dto.request.RoomTypeRequestDto;
//import com.indra.StaySmart.dto.response.RoomTypeResponseDto;
//import com.indra.StaySmart.entity.Hotel;
//import com.indra.StaySmart.entity.HotelRoomMappingId;
//import com.indra.StaySmart.entity.HotelRoomMappings;
//import com.indra.StaySmart.entity.RoomTypeEntity;
//import com.indra.StaySmart.enums.RoomCategory;
//import com.indra.StaySmart.repository.HotelRepository;
//import com.indra.StaySmart.repository.HotelRoomMappingsRepo;
//import com.indra.StaySmart.repository.RoomTypeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class RoomTypeServiceTest {
//
//    @Mock
//    private RoomTypeRepository roomTypeRepository;
//
//    @Mock
//    private HotelRepository hotelRepository;
//
//    @Mock
//    private HotelRoomMappingsRepo hotelRoomMappingsRepository;
//
//    @InjectMocks
//    private RoomTypeService roomTypeService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddRoom_Success() throws HotelNotFoundException {
//
//        UUID hotelId = UUID.fromString("bf01056e-2e99-471f-addc-4b41df9f258a");
//        UUID roomId = UUID.fromString("a38967ab-a091-4d01-aa3a-e655f4424514");
//
//        RoomTypeRequestDto requestDto = RoomTypeRequestDto.builder()
//                .roomCategory(RoomCategory.DELUXE)
//                .hotelId(hotelId)
//                .totalRooms(10)
//                .build();
//
//        Hotel hotel = new Hotel();
//        hotel.setHotelId(hotelId);
//
//        RoomTypeEntity roomTypeEntity = RoomTypeEntity.builder()
//                .roomId(roomId)
//                .roomCategory(RoomCategory.DELUXE)
//                .active(true)
//                .createdAt(LocalDate.now())
//                .build();
//
//        HotelRoomMappings hotelRoomMappings = new HotelRoomMappings();
//        hotelRoomMappings.setId(new HotelRoomMappingId(hotelId, roomId));
//        hotelRoomMappings.setHotel(hotel);
//        hotelRoomMappings.setRoomTypeEntity(roomTypeEntity);
//        hotelRoomMappings.setTotalRooms(10);
//
//        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotel));
//        when(roomTypeRepository.save(any(RoomTypeEntity.class))).thenReturn(roomTypeEntity);
//        when(hotelRoomMappingsRepository.save(any(HotelRoomMappings.class))).thenReturn(hotelRoomMappings);
//
//        RoomTypeResponseDto responseDto = roomTypeService.addRoom(requestDto);
//        assertEquals(roomId, responseDto.getRoomId());
//        assertEquals(RoomCategory.DELUXE, responseDto.getRoomCategory());
//
//        verify(hotelRepository, times(1)).findById(hotelId);
//        verify(roomTypeRepository, times(1)).save(any(RoomTypeEntity.class));
//        verify(hotelRoomMappingsRepository, times(1)).save(any(HotelRoomMappings.class));
//    }
//
//    @Test
//    public void testAddRoom_HotelNotFound() {
//        UUID hotelId = UUID.fromString("bf01056e-2e99-471f-addc-4v41df9f258a");
//
//        RoomTypeRequestDto requestDto = RoomTypeRequestDto.builder()
//                .roomCategory(RoomCategory.DELUXE)
//                .hotelId(hotelId)
//                .totalRooms(10)
//                .build();
//
//        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());
//
//        assertThrows(HotelNotFoundException.class, () -> roomTypeService.addRoom(requestDto));
//
//        verify(hotelRepository, times(1)).findById(hotelId);
//        verify(roomTypeRepository, times(0)).save(any(RoomTypeEntity.class));
//        verify(hotelRoomMappingsRepository, times(0)).save(any(HotelRoomMappings.class));
//    }
//}