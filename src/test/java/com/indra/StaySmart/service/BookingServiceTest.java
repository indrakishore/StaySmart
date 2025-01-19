//package com.indra.StaySmart.service;
//
//import com.indra.StaySmart.customException.BookingNotFoundException;
//import com.indra.StaySmart.customException.InventoryNotAvailableException;
//import com.indra.StaySmart.dto.request.BookingRequestDto;
//import com.indra.StaySmart.dto.response.BookingResponseDto;
//import com.indra.StaySmart.entity.Booking;
//import com.indra.StaySmart.entity.Customer;
//import com.indra.StaySmart.entity.PriceInventory;
//import com.indra.StaySmart.enums.BookingStatus;
//import com.indra.StaySmart.repository.BookingRepository;
//import com.indra.StaySmart.repository.CustomerRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class BookingServiceTest {
//
//    @Mock
//    private BookingRepository bookingRepo;
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @Mock
//    private PriceInventoryService priceInventoryService;
//
//    @InjectMocks
//    private BookingService bookingService;
//
//    @Test
//    public void testCreateBooking_Successful() {
//        BookingRequestDto requestDto = new BookingRequestDto();
//        UUID inventoryId = UUID.randomUUID();
//        UUID customerId = UUID.randomUUID();
//
//        requestDto.setInventoryId(inventoryId);
//        requestDto.setCustomerId(customerId);
//        requestDto.setCheckin(LocalDate.parse("2023-10-10"));
//        requestDto.setCheckout(LocalDate.parse("2023-10-15"));
//        requestDto.setBookingAmount(1000.0);
//        requestDto.setHotelId(UUID.randomUUID());
//        requestDto.setRoomId(UUID.randomUUID());
//
//        when(priceInventoryService.checkAvailability(inventoryId)).thenReturn(true);
//        when(priceInventoryService.getInventory(inventoryId)).thenReturn(Optional.of(new PriceInventory()));
//        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer()));
//        when(bookingRepo.save(any(Booking.class))).thenReturn(new Booking());
//
//        BookingResponseDto responseDto = bookingService.createBooking(requestDto);
//
//        assertNotNull(responseDto);
//        verify(priceInventoryService).checkAvailability(inventoryId);
//        verify(priceInventoryService).getInventory(inventoryId);
//        verify(customerRepository).findById(customerId);
//        verify(bookingRepo).save(any(Booking.class));
//        verify(priceInventoryService).updateInventory(inventoryId);
//    }
//
//    @Test
//    public void testCreateBooking_InventoryNotAvailable() {
//        BookingRequestDto requestDto = new BookingRequestDto();
//        UUID inventoryId = UUID.randomUUID();
//
//        requestDto.setInventoryId(inventoryId);
//        requestDto.setCustomerId(UUID.randomUUID());
//        requestDto.setCheckin(LocalDate.parse("2023-10-10"));
//        requestDto.setCheckout(LocalDate.parse("2023-10-15"));
//        requestDto.setBookingAmount(1000.0);
//        requestDto.setHotelId(UUID.randomUUID());
//        requestDto.setRoomId(UUID.randomUUID());
//
//        when(priceInventoryService.checkAvailability(inventoryId)).thenReturn(false);
//
//        assertThrows(InventoryNotAvailableException.class, () -> bookingService.createBooking(requestDto));
//        verify(priceInventoryService).checkAvailability(inventoryId);
//    }
//
//    @Test
//    public void testCreateBooking_CustomerNotFound() {
//        BookingRequestDto requestDto = new BookingRequestDto();
//        UUID inventoryId = UUID.randomUUID();
//        UUID customerId = UUID.randomUUID();
//
//        requestDto.setInventoryId(inventoryId);
//        requestDto.setCustomerId(customerId);
//        requestDto.setCheckin(LocalDate.parse("2023-10-10"));
//        requestDto.setCheckout(LocalDate.parse("2023-10-15"));
//        requestDto.setBookingAmount(1000.0);
//        requestDto.setHotelId(UUID.randomUUID());
//        requestDto.setRoomId(UUID.randomUUID());
//
//        when(priceInventoryService.checkAvailability(inventoryId)).thenReturn(true);
//        when(priceInventoryService.getInventory(inventoryId)).thenReturn(Optional.of(new PriceInventory()));
//        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> bookingService.createBooking(requestDto));
//        verify(priceInventoryService).checkAvailability(inventoryId);
//        verify(priceInventoryService).getInventory(inventoryId);
//        verify(customerRepository).findById(customerId);
//    }
//}