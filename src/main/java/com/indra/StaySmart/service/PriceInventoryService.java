package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.PriceInventoryDto;
import com.indra.StaySmart.dto.response.PriceInventoryResponseDto;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.PriceInventory;
import com.indra.StaySmart.repository.PriceInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PriceInventoryService {

    @Autowired
    private PriceInventoryRepository priceInventoryRepository;

    public boolean checkAvailability(UUID inventoryId) {
        // Your business logic to check inventory availability
        Optional<PriceInventory> priceInventoryOpt = priceInventoryRepository.findById(inventoryId);
        return priceInventoryOpt.isPresent() && priceInventoryOpt.get().getAvailableRooms() > 0;
    }

    @Transactional
    public boolean updateInventory(UUID inventoryId) {
        Optional<PriceInventory> priceInventoryOpt = priceInventoryRepository.findById(inventoryId);

        if (priceInventoryOpt.isEmpty()) {
            return false; // Inventory does not exist
        }

        PriceInventory priceInventory = priceInventoryOpt.get();
        if (priceInventory.getAvailableRooms() > 0) {
            priceInventory.setAvailableRooms(priceInventory.getAvailableRooms() - 1);
            priceInventoryRepository.save(priceInventory);
            return true;
        }

        return false; // Not enough inventory
    }

    public PriceInventoryResponseDto priceInventory(PriceInventoryDto priceInventoryDto) {
        PriceInventory priceInventory = convertDtoToEntity(priceInventoryDto);
        priceInventoryRepository.save(priceInventory);
        return convertEntityToResponseDto(priceInventory);
    }

    private PriceInventory convertDtoToEntity(PriceInventoryDto priceInventoryDto) {
        PriceInventory priceInventoryEntity = new PriceInventory();
        priceInventoryEntity.setHotelId(priceInventoryDto.getHotelId());
        priceInventoryEntity.setRoomId(priceInventoryDto.getRoomId());
        priceInventoryEntity.setPrice(priceInventoryDto.getPrice());
        priceInventoryEntity.setAvailableRooms(priceInventoryDto.getInventory());
        priceInventoryEntity.setStartDate(priceInventoryDto.getStartDate());
        priceInventoryEntity.setEndDate(priceInventoryDto.getEndDate());
        return priceInventoryEntity;
    }

    private PriceInventoryResponseDto convertEntityToResponseDto(PriceInventory priceInventory) {
        return PriceInventoryResponseDto.builder()
                .hotelId(priceInventory.getHotelId())
                .roomId(priceInventory.getRoomId())
                .price(priceInventory.getPrice())
                .startDate(priceInventory.getStartDate())
                .endDate(priceInventory.getEndDate())
                .isSoldOut(priceInventory.getIsSoldOut())
                .build();
    }

    private void decreaseInventory(PriceInventory priceInventory) {
        // Business logic to decrease inventory
        priceInventory.setAvailableRooms(priceInventory.getAvailableRooms() - 1);
        priceInventoryRepository.save(priceInventory);
    }

    public List<PriceInventoryResponseDto> getAvailableHotelsByMinPrice(List<Hotel> hotelList, LocalDate checkin) {
        List<PriceInventoryResponseDto> result = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            List<PriceInventory> inventories = priceInventoryRepository.findByHotelIdAndCheckin(hotel.getHotelId(), checkin);
            inventories.stream()
                    .map(this::convertEntityToResponseDto)
                    .forEach(result::add);
        }
        return result;
    }

    public List<PriceInventoryResponseDto> getPriceAndInventoryForHotel(UUID hotelId, LocalDate checkin, LocalDate checkout) {
        List<PriceInventoryResponseDto> priceInventoryResponseDtoList = new ArrayList<>();
        List<PriceInventory> priceInventoryDetails = priceInventoryRepository.findByHotelIdAndCheckin(hotelId, checkin);

        for (PriceInventory priceInventoryDetail : priceInventoryDetails) {
            PriceInventoryResponseDto responseDto = convertEntityToResponseWithSoldOutCheck(priceInventoryDetail);
            if (priceInventoryDetail.getEndDate().isAfter(checkout)) {
                priceInventoryResponseDtoList.add(responseDto);
            }
        }

        return priceInventoryResponseDtoList;
    }

    private PriceInventoryResponseDto convertEntityToResponseWithSoldOutCheck(PriceInventory priceInventory) {
        Boolean isSoldOut = isHotelSoldOut(priceInventory.getAvailableRooms());
        return PriceInventoryResponseDto.builder()
                .hotelId(priceInventory.getHotelId())
                .roomId(priceInventory.getRoomId())
                .price(priceInventory.getPrice())
                .startDate(priceInventory.getStartDate())
                .endDate(priceInventory.getEndDate())
                .isSoldOut(isSoldOut)
                .build();
    }

    private Boolean isHotelSoldOut(Integer availableRooms) {
        return availableRooms <= 0;
    }


    public Optional<Object> getInventory(UUID inventoryId) {
        return priceInventoryRepository.findById(inventoryId).map(priceInventory -> (Object) priceInventory);
    }

    public List<PriceInventoryResponseDto> getPriceAndInvetoryForHotel(Integer hotelId, LocalDate checkin) {
        return null;
    }

    public PriceInventoryResponseDto getInventory(UUID hotelId, LocalDate checkin) {
        List<PriceInventory> priceInventoryDetails = priceInventoryRepository.findByHotelIdAndCheckin(hotelId, checkin);

        if (priceInventoryDetails.isEmpty()) {
            return null; // or throw an appropriate exception
        }

        // Assuming we return the first found inventory, you may want to handle multiple results differently
        PriceInventory priceInventory = priceInventoryDetails.get(0);
        return convertEntityToResponseDto(priceInventory);
    }
}

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

//    private PriceInventoryResponseDto convertpriceInventoryDetailsToPriceInventoryResponseDto(com.indra.StaySmart.entity.PriceInventory priceInventory) {
//        Boolean isSoldOut = isHotelSoldOut(priceInventory.getAvailableRooms());
//        return PriceInventoryResponseDto.builder().isSoldOut(isSoldOut).price(priceInventory.getPrice()).date(priceInventory.getDate()).build();
//    }

//    private Boolean isHotelSoldOut(Integer availableRooms) {
//        return availableRooms < 0;
//
//    }

