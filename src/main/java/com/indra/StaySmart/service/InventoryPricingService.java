package com.indra.StaySmart.service;

import com.indra.StaySmart.dto.request.InventoryPricingDto;
import com.indra.StaySmart.entity.PricingInventory;
import com.indra.StaySmart.entity.Hotel;
import com.indra.StaySmart.entity.Room;
import com.indra.StaySmart.repository.HotelRepository;
import com.indra.StaySmart.repository.InventoryPricingRepository;
import com.indra.StaySmart.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryPricingService {

    @Autowired
    private InventoryPricingRepository inventoryPricingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public String addInventoryPricing(InventoryPricingDto inventoryPricingDto) {
        // Fetch Hotel and Room entities from the database
        Hotel hotel = hotelRepository.findById(inventoryPricingDto.getHotelId())
                .orElseThrow(() -> new EntityNotFoundException("Hotel"));
        Room room = roomRepository.findByRoomId(inventoryPricingDto.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException("Room"));

        // Validate the date range (start date before end date)
        if (inventoryPricingDto.getStartDate().isAfter(inventoryPricingDto.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        // Check if a record already exists for this hotel, room, and date
        Optional<PricingInventory> existingInventory = inventoryPricingRepository
                .findByHotelAndRoomAndDate(hotel, room, inventoryPricingDto.getStartDate());

        if (existingInventory.isPresent()) {
            return "Inventory and pricing entry already exists for this hotel, room, and date.";
        }

        // Create and save new PricingInventory entity
        PricingInventory inventory = new PricingInventory();
        inventory.setHotel(hotel);
        inventory.setRoom(room);
        inventory.setDate(inventoryPricingDto.getStartDate());
        inventory.setPrice(inventoryPricingDto.getPrice());
        inventory.setInventory(inventoryPricingDto.getInventory());

        inventoryPricingRepository.save(inventory);

        return "Inventory and pricing added successfully.";
    }
}
