package com.indra.StaySmart.service;

import com.indra.StaySmart.customException.BookingNotFoundException;
import com.indra.StaySmart.customException.InventoryNotAvailableException;
import com.indra.StaySmart.dto.NotificationDataDto;
import com.indra.StaySmart.dto.request.BookingRequestDto;
import com.indra.StaySmart.dto.response.BookingResponseDto;
import com.indra.StaySmart.entity.Booking;
import com.indra.StaySmart.entity.Customer;
import com.indra.StaySmart.entity.PriceInventory;
import com.indra.StaySmart.enums.BookingStatus;
import com.indra.StaySmart.interfaces.NotificationService;
import com.indra.StaySmart.repository.BookingRepository;
import com.indra.StaySmart.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PriceInventoryService priceInventoryService;

    @Autowired
    private NotificationService notificationService;

    /**
     * Creates a new booking.
     *
     * @param bookingRequestDto The request DTO containing booking details.
     * @return A response DTO containing the booking details.
     */
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        // Validate the request DTO
        validateBookingRequest(bookingRequestDto);

        // Get inventoryId from booking request DTO and check availability
        UUID inventoryId = bookingRequestDto.getInventoryId();
        boolean isAvailable = priceInventoryService.checkAvailability(inventoryId);
        if (!isAvailable) {
            logger.error("Inventory not available for: {}", inventoryId);
            throw new InventoryNotAvailableException("Inventory not available.");
        }

        // Retrieve the PriceInventory entity to set the relationship
        PriceInventory inventory = (PriceInventory) priceInventoryService.getInventory(inventoryId)
                .orElseThrow(() -> new InventoryNotAvailableException("Inventory not available."));

        // Retrieve the Customer entity
        UUID customerId = bookingRequestDto.getCustomerId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        logger.info("Customer retrieved: {}", customer);

        // Create a new Booking entity and set its properties
        Booking booking = new Booking();
        booking.setInventory(inventory);
        booking.setCustomer(customer);
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setHotelId(bookingRequestDto.getHotelId());
        booking.setRoomId(bookingRequestDto.getRoomId());
        booking.setCheckIn(bookingRequestDto.getCheckin());
        booking.setCheckOut(bookingRequestDto.getCheckout());
        booking.setBookingAmount(bookingRequestDto.getBookingAmount());
        booking.setPrepaid(true); // Assuming it's set to prepaid; change as needed

        logger.info("Booking before saving: {}", booking);

        // Save the new booking entity to the database
        booking = bookingRepo.save(booking);

        logger.info("Booking after saving: {}", booking);

        // Update inventory status
        priceInventoryService.updateInventory(inventoryId);

        // Map the Booking entity to BookingResponseDto and return it
        BookingResponseDto bookingResponseDto = mapToBookingResponseDto(booking);

        // Logging response info
        logger.info("Booking Response: {}", bookingResponseDto);

        //send email
//        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        NotificationDataDto notificationDataDto = getNotificationSenderDto(bookingRequestDto.getCustomerId());
        notificationService.sendNotification(notificationDataDto);

        return bookingResponseDto;
    }

    private NotificationDataDto getNotificationSenderDto(UUID customerId) {
        Optional<Customer> customerOptional = customerRepository.findByCustomerId(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            NotificationDataDto notificationDataDto = new NotificationDataDto();
            notificationDataDto.setText("Congratulations! " + customer.getName() + " Your Booking has been confirmed!\n enjoy, Have a great Stay. Thanks" );
            notificationDataDto.setUserEMail(customer.getEmail());
            notificationDataDto.setSubject(customer.getName() + "Booking has been confirmed.");
            return notificationDataDto;
        }
        return null;
    }

    /**
     * Validates the booking request DTO.
     *
     * @param bookingRequestDto The request DTO to validate.
     */
    private void validateBookingRequest(BookingRequestDto bookingRequestDto) {
        if (bookingRequestDto.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }
        if (bookingRequestDto.getInventoryId() == null) {
            throw new IllegalArgumentException("Inventory ID must not be null");
        }
        if (bookingRequestDto.getCheckin() == null || bookingRequestDto.getCheckout() == null) {
            throw new IllegalArgumentException("Check-in and Check-out dates must not be null");
        }
        // Add more validation as needed
    }

    /**
     * Maps a Booking entity to a BookingResponseDto.
     *
     * @param booking The Booking entity to map.
     * @return A BookingResponseDto.
     */
    private BookingResponseDto mapToBookingResponseDto(Booking booking) {
        BookingResponseDto responseDto = new BookingResponseDto();
        responseDto.setBookingId(booking.getBookingId());
        responseDto.setCustomerId(booking.getCustomer().getCustomerId()); // Set the entire Customer object
        responseDto.setInventoryId(booking.getInventory().getId());
        responseDto.setStatus(booking.getBookingStatus());
        return responseDto;
    }

    /**
     * Cancels an existing booking.
     *
     * @param bookingId The ID of the booking to cancel.
     * @return True if the booking was successfully cancelled, false otherwise.
     */
    @Transactional
    public Boolean cancelBooking(UUID bookingId) {
        // Retrieve the booking from the repository
        Optional<Booking> optionalBooking = bookingRepo.findByBookingId(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            // Validate if the booking is not already cancelled
            if (booking.getBookingStatus() != BookingStatus.CANCELLED) {
                // Update the booking status to CANCELLED
                booking.setBookingStatus(BookingStatus.CANCELLED);
                bookingRepo.save(booking);

                // Increase inventory to reflect the cancelled booking
                priceInventoryService.updateInventory(booking.getInventory().getId());

                return true;
            } else {
                // The booking is already cancelled
                return false;
            }
        } else {
            // The booking does not exist
            return false;
        }
    }

    /**
     * Retrieves the booking details by booking ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return A BookingResponseDto containing the booking details.
     */
    public BookingResponseDto getBooking(UUID bookingId) throws BookingNotFoundException {
        Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);

        if (optionalBooking.isPresent()) {
            return new BookingResponseDto(optionalBooking.get());
        } else {
            throw new BookingNotFoundException("Booking not found with id: " + bookingId);
        }
    }

    /**
     * Updates the details of an existing booking.
     *
     * @param requestDto The request DTO containing updated booking details.
     * @return A BookingResponseDto containing the updated booking details.
     * @throws BookingNotFoundException if the booking is not found.
     */
    public BookingResponseDto updateBookingDetails(BookingRequestDto requestDto) throws BookingNotFoundException {
        UUID bookingId = requestDto.getBookingId();
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + bookingId));

        // Update booking details from the request DTO
        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        booking.setCheckIn(requestDto.getCheckin());
        booking.setCheckOut(requestDto.getCheckout());
        booking.setCustomer(customer);
        booking.setBookingAmount(requestDto.getBookingAmount());
        booking.setPrepaid(true); // Update as needed

        // Save the updated booking entity to the database
        booking = bookingRepo.save(booking);

        // Map the Booking entity to BookingResponseDto and return it
        return mapToBookingResponseDto(booking);
    }
}