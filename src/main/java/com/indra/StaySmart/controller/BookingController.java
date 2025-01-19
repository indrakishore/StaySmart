package com.indra.StaySmart.controller;

import com.indra.StaySmart.customException.BookingNotFoundException;
import com.indra.StaySmart.dto.request.BookingRequestDto;
import com.indra.StaySmart.dto.response.BookingResponseDto;
import com.indra.StaySmart.entity.Customer;
import com.indra.StaySmart.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;


    @PostMapping("/create")
    public BookingResponseDto createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        return bookingService.createBooking(bookingRequestDto);
    }

    @GetMapping("/cancel/{bookingId}")
    public Boolean cancelBooking(@PathVariable UUID bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @PutMapping
    public BookingResponseDto updateBooking(@RequestBody BookingRequestDto requestDto) throws BookingNotFoundException {
        return bookingService.updateBookingDetails(requestDto);
    }

    //you have to find those customers those have multiple booking in single hotel
    @GetMapping("/findCustomer")
    public List<Customer> findCustomerWithMultipleBooking() {
        return bookingService.findCustomerWithMultipleBooking();
    }

    /**
     * 5. Booking APIs (Optional)
     * Book a Room:
     *
     * Endpoint: POST /api/v1/bookings
     * Description: Book a room for a specific customer.
     * Request Body:
     * json
     * Copy code
     * {
     *   "customerId": "c6c9d8f4-8b26-4c6f-9f70-c8bb35c7e11b",
     *   "roomId": "1cf00644-09d7-43f9-9962-91cc2557d204",
     *   "checkInDate": "2024-11-10",
     *   "checkOutDate": "2024-11-15",
     *   "price": 12500.00
     * }
     * Get Booking Details:
     *
     * Endpoint: GET /api/v1/bookings/{bookingId}
     * Description: Retrieve details of a specific booking.
     * Response Body:
     * json
     * Copy code
     * {
     *   "bookingId": "1234abcd",
     *   "customerId": "c6c9d8f4-8b26-4c6f-9f70-c8bb35c7e11b",
     *   "roomId": "1cf00644-09d7-43f9-9962-91cc2557d204",
     *   "checkInDate": "2024-11-10",
     *   "checkOutDate": "2024-11-15",
     *   "totalPrice": 12500.00
     * }
     */
}
