package com.indra.StaySmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "price_inventory",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"hotel_id", "room_id", "date"})
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hotel_id")
    private UUID hotelId;

    @Column(name = "room_id")
    private UUID roomId;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name = "isSoldOut")
    private Boolean isSoldOut;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "available_rooms")
    private Integer availableRooms;

    @Column(name = "price")
    private Double price;

}
