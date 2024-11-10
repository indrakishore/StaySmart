package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.InventoryPricingDto;
import com.indra.StaySmart.dto.response.PriceInventoryResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.PriceInventory;
import com.indra.StaySmart.repository.PriceInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class PriceInventoryService {

    @Autowired
    private PriceInventoryRepository priceInventoryRepository;

//    private  void updateInventory(){

    // write query which will decrease inventory

//    priceInventoryRepository.
//
//    }


//    public List<PriceInventoryResponseDto> getAvailableHotelsByMinPrice(List<Hotel> hotelList, LocalDate checkin) {
//        return null;
//
//    }

    // checkout also requried as parameter
//    public List<PriceInventoryResponseDto> getPriceAndInvetoryForHotel(Integer hotelId, LocalDate checkin) {
//        List<PriceInventoryResponseDto> priceInventoryResponseDtoList = new ArrayList<>();
//        List<PriceInventory> priceInventoryDetails = priceInventoryRepository.findByHotelIdAndCheckin(hotelId, checkin);
//        for (PriceInventory priceInventoryDetail : priceInventoryDetails) {
//            PriceInventoryResponseDto responseDto = convertpriceInventoryDetailsToPriceInventoryResponseDto(priceInventoryDetail);
//            priceInventoryResponseDtoList.add(responseDto);
//
//        }
//        return priceInventoryResponseDtoList;
//
//    }

    private PriceInventoryResponseDto convertpriceInventoryDetailsToPriceInventoryResponseDto(PriceInventory priceInventory) {
        Boolean isSoldOut = isHotelSoldOut(priceInventory.getAvailableRooms());
        return PriceInventoryResponseDto.builder().isSoldOut(isSoldOut).price(priceInventory.getPrice()).date(priceInventory.getDate()).build();
    }

    private Boolean isHotelSoldOut(Integer availableRooms) {
        return availableRooms < 0;

    }


    public String addInventoryPricing(InventoryPricingDto inventoryPricingDto) {
        return null;
    }
}
