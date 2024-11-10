package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.BookingRequestDto;
import com.indra.StaySmart.dto.response.BookingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingService {

//    @Autowired
//    BookingRepo bookingRepo;


    @Autowired
    private PriceInventoryService priceInventoryService;

    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto){

        // validate request
        // inventory available or not? and get inventory priceInventoryService.checkAvailablilty()
        // create with status -> CREATED // bookingRepo.save(booking);
        // update Inventory // priceInventoryService.updateInventory();
        // return booking response

        return null;

    }

    public Boolean cancelBooking(Integer bookingId){
        // validate if booking is not in cancelled state  and is present in our database
        // status -> CANCCLLED // bookingRepo.save();
        // increase inventory // priceInventoryService.updateInventory();
        return true;
    }

    public BookingResponseDto getBooking(Integer bookingId){
        // findById
        return null;
    }





}
