package com.indra.StaySmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    @Column(name="name")
    String name;

    @Column(name="phone_number")
    String phoneNumber;

    @Column(name="email")
    String email;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    AdharDetails adharDetails;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Booking> bookingsList= new ArrayList<>();

}
