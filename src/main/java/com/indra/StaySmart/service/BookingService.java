package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.InventoryNotAvailableException;
import com.indra.StaySmart.dto.request.BookingRequestDto;
import com.indra.StaySmart.dto.response.BookingResponseDto;
import com.indra.StaySmart.entity.Booking;
import com.indra.StaySmart.enums.BookingStatus;
import com.indra.StaySmart.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class BookingService {

//    @Autowired
//    BookingRepo bookingRepo;


    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private PriceInventoryService priceInventoryService;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        // validate request
        validateBookingRequest(bookingRequestDto);

        // Check inventory availability and get price
        UUID inventoryId = bookingRequestDto.getInventoryId();
        boolean isAvailable = priceInventoryService.checkAvailablilty(inventoryId);
        if (!isAvailable) {
            throw new InventoryNotAvailableException("Inventory not available.");
        }

        // Create booking and set status to CREATED
        Booking booking = new Booking();
        booking.setInventoryId(inventoryId);
        booking.setCustomerId(bookingRequestDto.getCustomerId());
        booking.setBookingStatus(BookingStatus.valueOf("CONFIRMED"));

        // Save booking
        booking = bookingRepo.save(booking);

        // Update inventory
        priceInventoryService.updateInventory(inventoryId);

        // Map booking to BookingResponseDto
        BookingResponseDto bookingResponseDto = mapToBookingResponseDto(booking);

        // Return booking response
        return bookingResponseDto;
    }

    private void validateBookingRequest(BookingRequestDto bookingRequestDto) {
        // Example validation logic
        if (bookingRequestDto.getCustomerId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (bookingRequestDto.getInventoryId() == null) {
            throw new IllegalArgumentException("Inventory ID must not be null");
        }
    }

    private BookingResponseDto mapToBookingResponseDto(Booking booking) {
        // Map the booking entity to BookingResponseDto
        BookingResponseDto responseDto = new BookingResponseDto();
        responseDto.setBookingId(booking.getBookingId());
        responseDto.setCustomerId(booking.getCustomerId());
        responseDto.setInventoryId(booking.getInventoryId());
        responseDto.setStatus(booking.getBookingStatus());
        return responseDto;
    }


    @Transactional
    public Boolean cancelBooking(UUID bookingId) {
        // Retrieve the booking from the repository using the bookingId
        Optional<Booking> optionalBooking = bookingRepository.findByBookingId(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            // Validate if booking is not already cancelled
            if (!"CANCELLED".equals(booking.getBookingStatus())) {

                // Update status to CANCELLED
                booking.setBookingStatus(BookingStatus.valueOf("CANCELLED"));

                // Save the updated booking
                bookingRepository.save(booking);

                // Increase inventory
                priceInventoryService.updateInventory(booking.getInventoryId());

                return true;
            } else {
                // Booking is already cancelled
                return false;
            }
        } else {
            // Booking does not exist
            return false;
        }
    }

    public BookingResponseDto getBooking(UUID bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            return new BookingResponseDto(booking);
        } else {
            return null;
        }
    }
}

        // validate if booking is not in cancelled state  and is present in our database
        // status -> CANCCLLED // bookingRepo.save();
        // increase inventory // priceInventoryService.updateInventory();

        // findById



