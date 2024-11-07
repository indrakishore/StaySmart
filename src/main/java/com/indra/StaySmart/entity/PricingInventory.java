package com.indra.StaySmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "pricing_inventory",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"hotel_id", "room_id", "date"})
        }
)
@Data
public class PricingInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer inventory;

    @Column(nullable = false) // Precision for currency fields
    private Double price;

}
