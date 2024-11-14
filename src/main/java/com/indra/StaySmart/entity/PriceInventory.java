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

    // These fields ensure we can quickly query by ids
    @Column(name = "hotel_id", insertable = false, updatable = false)
    private UUID hotelId;

    @Column(name = "room_id", insertable = false, updatable = false)
    private UUID roomId;

//    @ManyToOne
//    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
//    private Hotel hotel;
//
//    @ManyToOne
//    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
//    private RoomTypeEntity roomTypeEntity;

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