package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.HotelRequestDto;
import com.indra.StaySmart.dto.response.HotelResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.enums.HotelStatus;
import com.indra.StaySmart.repository.HotelRepository;
import com.indra.StaySmart.repository.HotelRoomMappingsRepo;
import com.indra.StaySmart.repository.RoomTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomTypeRepository roomTypeRepository;

    @Mock
    private HotelRoomMappingsRepo hotelRoomMappingsRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    public void testAddHotel() {
        // Arrange
        HotelRequestDto hotelRequestDto = new HotelRequestDto();
        hotelRequestDto.setHotelId(UUID.randomUUID());
        hotelRequestDto.setHotelName("Test Hotel");
        hotelRequestDto.setHotelAddress("Test Address");
        hotelRequestDto.setCity("Test City");
        hotelRequestDto.setContactNumber("1234567890");
        hotelRequestDto.setStatus(HotelStatus.ACTIVE);
        hotelRequestDto.setRating(4.7);

        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelRequestDto.getHotelId());
        hotel.setHotelName(hotelRequestDto.getHotelName());
        hotel.setAddress(hotelRequestDto.getHotelAddress());
        hotel.setCity(hotelRequestDto.getCity());
        hotel.setContactNumber(hotelRequestDto.getContactNumber());
        hotel.setStatus(hotelRequestDto.getStatus());
        hotel.setRating(hotelRequestDto.getRating());

        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        // Act
        HotelResponseDto responseDto = hotelService.addHotel(hotelRequestDto);

        // Assert
        assertNotNull(responseDto);
        assertEquals(hotel.getHotelId(), responseDto.getHotelId());
        assertEquals(hotel.getHotelName(), responseDto.getHotelName());
        assertEquals(hotel.getAddress(), responseDto.getHotelAddress());
        assertEquals(hotel.getCity(), responseDto.getCity());
        assertEquals(hotel.getContactNumber(), responseDto.getContactNumber());
        assertNotNull(responseDto, "responseDto should not be null");
        assertNotNull(responseDto.getStatus(), "Status in responseDto should not be null");
        assertEquals(HotelStatus.ACTIVE, responseDto.getStatus(), "Status should be ACTIVE");

        assertNotNull(hotel, "hotel should not be null");
        assertNotNull(hotel.getStatus(), "Status in hotel should not be null");
        assertEquals(hotel.getStatus(), responseDto.getStatus());        assertEquals(hotel.getRating(), responseDto.getRating());
    }
}