//package com.indra.StaySmart.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.reset;
//import static org.mockito.Mockito.when;
//
//import com.indra.StaySmart.dto.request.PriceInventoryDto;
//import com.indra.StaySmart.dto.response.PriceInventoryResponseDto;
//import com.indra.StaySmart.entity.PriceInventory;
//import com.indra.StaySmart.repository.PriceInventoryRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.UUID;
//
//@SpringBootTest
//public class PriceInventoryServiceTest {
//
//    @Mock
//    private PriceInventoryRepository priceInventoryRepository;
//
//    @InjectMocks
//    private PriceInventoryService priceInventoryService;
//
//    @BeforeEach
//    void setUp() {
//        reset(priceInventoryRepository);
//    }
//
//    @Test
//    public void testPriceInventory() {
//        PriceInventoryDto dto = new PriceInventoryDto();
//        // Set fields in dto
//        dto.setHotelId(UUID.randomUUID());
//        dto.setRoomId(UUID.randomUUID());
//        dto.setPrice(100.0);
//        dto.setStartDate(LocalDate.now());
//        dto.setEndDate(LocalDate.now().plusDays(7));
//        dto.setInventory(10);
//
//        when(priceInventoryRepository.save(any(PriceInventory.class)))
//                .thenAnswer(invocation -> invocation.getArgument(0));
//
//        PriceInventoryResponseDto response = priceInventoryService.priceInventory(dto);
//
//        assertNotNull(response);
//        assertEquals(dto.getHotelId(), response.getHotelId());
//        assertEquals(dto.getRoomId(), response.getRoomId());
//        assertEquals(dto.getPrice(), response.getPrice());
//    }
//
//    @Test
//    public void testCheckAvailability() {
//        UUID inventoryId = UUID.randomUUID();
//
//        PriceInventory priceInventory = new PriceInventory();
//        // Set fields in priceInventory
//        priceInventory.setAvailableRooms(5);
//
//        when(priceInventoryRepository.findById(inventoryId)).thenReturn(Optional.of(priceInventory));
//
//        boolean isAvailable = priceInventoryService.checkAvailability(inventoryId);
//
//        assertTrue(isAvailable);
//    }
//
//    @Test
//    public void testUpdateInventory() {
//        UUID inventoryId = UUID.randomUUID();
//
//        PriceInventory priceInventory = new PriceInventory();
//        // Set fields in priceInventory
//        priceInventory.setAvailableRooms(5);
//
//        when(priceInventoryRepository.findById(inventoryId)).thenReturn(Optional.of(priceInventory));
//        when(priceInventoryRepository.save(any(PriceInventory.class)))
//                .thenAnswer(invocation -> invocation.getArgument(0));
//
//        boolean result = priceInventoryService.updateInventory(inventoryId);
//
//        assertTrue(result);
//    }
//}