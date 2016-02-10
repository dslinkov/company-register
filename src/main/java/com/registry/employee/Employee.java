package com.registry.employee;

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
public class Employee {

    private BigDecimal id;
    private String firstName;
    private String lastName;
    private BigDecimal companyId;
    private String position;

}
