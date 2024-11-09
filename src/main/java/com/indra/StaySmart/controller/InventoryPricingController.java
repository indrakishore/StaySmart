package com.indra.StaySmart.controller;

import com.indra.StaySmart.dto.request.InventoryPricingDto;
import com.indra.StaySmart.service.InventoryPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pricing-inventory") // Adds new pricing and inventory information for a specific hotel, room, and date.
public class InventoryPricingController {

    @Autowired
    InventoryPricingService inventoryPricingService;

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody InventoryPricingDto inventoryPricingDto) {
        String response = inventoryPricingService.addInventoryPricing(inventoryPricingDto);
        return ResponseEntity.ok(response);
    }

    //todo
    /***
     * Set Pricing for a Room:
     * Endpoint: POST /api/v1/rooms/{roomId}/pricing
     * Description: Set the pricing for a specific room.
     * Request Body:
     */

    /***
     * Get Pricing Information for a Room:
     *
     * Endpoint: GET /api/v1/rooms/{roomId}/pricing
     * Description: Get the pricing and inventory details for a specific room.
     */

    /**
     * Update Pricing for a Room:
     *
     * Endpoint: PUT /api/v1/rooms/{roomId}/pricing/{pricingId}
     * Description: Update the pricing for a specific room and date range.
     * */

    /**
     * Delete Pricing for a Room:
     *
     * Endpoint: DELETE /api/v1/rooms/{roomId}/pricing/{pricingId}
     * Description: Delete pricing for a specific room and date range.
     */

    /**
     * Room Availability APIs
     * Check Room Availability:
     *
     * Endpoint: GET /api/v1/rooms/{roomId}/availability
     * Description: Check the availability of a room within a specific date range.
     * Request Parameters:
     * startDate: Date (e.g., 2024-11-10)
     * endDate: Date (e.g., 2024-11-20)
     * Response Body:
     * json
     *
     * {
     *   "roomId": "1cf00644-09d7-43f9-9962-91cc2557d204",
     *   "availableInventory": 5
     * }
     * **/


    /**
     * Update Room Availability:
     *
     * Endpoint: PUT /api/v1/rooms/{roomId}/availability
     * Description: Update the availability of a specific room.
     * Request Body:
     * json
     * Copy code
     * {
     *   "startDate": "2024-11-10",
     *   "endDate": "2024-11-20",
     *   "inventory": 4
     * /}
     *
    //Search and Filter API
    /**
     * 6. Search and Filter APIs
     * Search Rooms by Hotel and Date Range:
     * Endpoint: GET /api/v1/hotels/{hotelId}/rooms/search
     * Description: Search for available rooms in a hotel within a specified date range.
     * Request Parameters:
     * startDate: Date (e.g., 2024-11-10)
     * endDate: Date (e.g., 2024-11-20)
     * Response Body:
     * json
     * Copy code
     * {
     *   "hotelId": "670efd7f-6e0c-40f3-a7a4-c5b578b14123",
     *   "rooms": [
     *     {
     *       "roomId": "1cf00644-09d7-43f9-9962-91cc2557d204",
     *       "roomName": "Deluxe Suite",
     *       "price": 2500.00
     *     }
     *   ]
     * }
     * ***/

}
