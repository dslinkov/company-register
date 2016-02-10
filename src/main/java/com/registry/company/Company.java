package com.registry.company;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {

    private BigDecimal id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String email;

}
