package com.registry.employee;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.registry.util.CompanyConversionUtil;

@RestController
public class EmploeeResource {

    @Inject
    private EmployeeService employeeService;

    @RequestMapping(value = "/api/company/{company-id}/employee", method = RequestMethod.GET)
    public HttpEntity<EmployeeDetails> getAllCompanyEmployees(@PathVariable(value = "company-id") BigDecimal companyId) {

        List<Employee> employees = employeeService.getAllEmployees(companyId);

        return new ResponseEntity<>(EmployeeDetails.builder().employees(employees).build(), HttpStatus.OK);

    }

    @RequestMapping(value = "/api/company/{company-id}/employee", method = RequestMethod.POST)
    public HttpEntity<Employee> createNewCompanyEmployee(@PathVariable(value = "company-id") BigDecimal companyId,
            @RequestBody EmployeeDto employeeDto) {

        Employee employee = CompanyConversionUtil.mapClasses(employeeDto, Employee.class);
        employee.setCompanyId(companyId);

        employee = employeeService.createNewCompanyEmployee(employee);

        return new ResponseEntity<>(employee, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/company/{company-id}/employee/{employee-id}", method = RequestMethod.PUT)
    public HttpEntity<Employee> updateCompanyEmployee(@PathVariable(value = "company-id") BigDecimal companyId,
            @PathVariable(value = "employee-id") BigDecimal employeeId, @RequestBody EmployeeDto employeeDto) {

        Employee employee = CompanyConversionUtil.mapClasses(employeeDto, Employee.class);
        employee.setId(employeeId);
        employee.setCompanyId(companyId);

        employee = employeeService.updateCompanyEmployee(employee);

        return new ResponseEntity<>(employee, HttpStatus.OK);

    }

}
