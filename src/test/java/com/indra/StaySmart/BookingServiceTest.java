//package com.indra.StaySmart;
//
//import com.indra.StaySmart.customException.InventoryNotAvailableException;
//import com.indra.StaySmart.dto.request.BookingRequestDto;
//import com.indra.StaySmart.dto.response.BookingResponseDto;
//import com.indra.StaySmart.entity.Booking;
//import com.indra.StaySmart.enums.BookingStatus;
//import com.indra.StaySmart.repository.BookingRepository;
//import com.indra.StaySmart.service.BookingService;
//import com.indra.StaySmart.service.PriceInventoryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class BookingServiceTest {
//
//    @InjectMocks
//    private BookingService bookingService;
//
//    @Mock
//    private PriceInventoryService priceInventoryService;
//
//    @Mock
//    private BookingRepository bookingRepo;
//
//    @Captor
//    ArgumentCaptor<Booking> bookingCaptor;
//
//    private BookingRequestDto bookingRequestDto;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        bookingRequestDto = new BookingRequestDto();
//        bookingRequestDto.setInventoryId(UUID.randomUUID());
//        bookingRequestDto.setCustomerId(UUID.randomUUID());
//    }
//
//    @Test
//    void createBooking_whenInventoryIsAvailable_shouldCreateBooking() {
//        UUID inventoryId = bookingRequestDto.getInventoryId();
//
//        when(priceInventoryService.checkAvailablilty(inventoryId)).thenReturn(true);
//
//        Booking savedBooking = new Booking();
//        savedBooking.setBookingId(UUID.randomUUID());
//        when(bookingRepo.save(any(Booking.class))).thenReturn(savedBooking);
//
//        BookingResponseDto responseDto = bookingService.createBooking(bookingRequestDto);
//
//        verify(priceInventoryService).checkAvailablilty(inventoryId);
//        verify(bookingRepo).save(bookingCaptor.capture());
//        verify(priceInventoryService).updateInventory(inventoryId);
//
//        Booking capturedBooking = bookingCaptor.getValue();
//        assertEquals(inventoryId, capturedBooking.getInventoryId());
//        assertEquals(bookingRequestDto.getCustomerId(), capturedBooking.getCustomerId());
//        assertEquals(BookingStatus.CONFIRMED, capturedBooking.getBookingStatus());
//        assertNotNull(responseDto);
//    }
//
//    @Test
//    void createBooking_whenInventoryIsNotAvailable_shouldThrowException() {
//        UUID inventoryId = bookingRequestDto.getInventoryId();
//
//        when(priceInventoryService.checkAvailablilty(inventoryId)).thenReturn(false);
//
//        assertThrows(InventoryNotAvailableException.class, () -> bookingService.createBooking(bookingRequestDto));
//
//        verify(priceInventoryService).checkAvailablilty(inventoryId);
//        verify(bookingRepo, never()).save(any());
//        verify(priceInventoryService, never()).updateInventory(any());
//    }
//}
