package com.indra.StaySmart.dto.request;

import com.indra.StaySmart.entity.AdharDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    private String name;
    private String email;
    private String phone;
    private String city;
    private AdharDetails adharDetails;
}
