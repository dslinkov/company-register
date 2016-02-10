package com.registry.employee;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(BigDecimal companyId) {
        return employeeRepository.findAll(companyId);
    }

    public Employee createNewCompanyEmployee(Employee employee) {

        return employeeRepository.insert(employee);
    }

    public Employee updateCompanyEmployee(Employee employee) {

        return employeeRepository.update(employee);
    }

}
