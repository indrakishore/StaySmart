package com.indra.StaySmart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adhar_details")
public class AdharDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adharId;

    @Column(name = "url")
    private String url;

    @Column(name = "dob")
    private String dob;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    Customer customer;

//    public void setAdharNumber(String number) {
//    }
}
