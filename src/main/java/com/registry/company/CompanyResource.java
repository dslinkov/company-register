package com.registry.company;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.registry.util.CompanyConversionUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CompanyResource {

    @Inject
    private CompanyService companyService;

    // create new copmany
    // list all companies
    // details about each company
    // update a company
    // , consumes = {MediaType.APPLICATION_JSON_VALUE}

    @RequestMapping(value = "/api/company", method = RequestMethod.GET)
    public HttpEntity<CompanyDetails> getAllCompanies() {

        log.info("{}", "");

        List<CompanyDetailDto> companies = companyService.getAllCompanies();

        return new ResponseEntity<>(CompanyDetails.builder().companies(companies).build(), HttpStatus.OK);

    }

    @RequestMapping(value = "/api/company/{company-id}", method = RequestMethod.GET)
    public HttpEntity<CompanyDetailDto> getCompany(@PathVariable(value = "company-id") BigDecimal companyId) {

        log.info("{} ", "");

        CompanyDetailDto companyDetail = companyService.getCompany(companyId);

        return new ResponseEntity<>(companyDetail, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/company", method = RequestMethod.POST)
    public HttpEntity<CompanyDetailDto> createNewCompany(@Validated @RequestBody CompanyDto companyDto) {

        Company company = CompanyConversionUtil.mapClasses(companyDto, Company.class);

        log.info("getSampleCount Partner ID: {} ", companyDto);

        CompanyDetailDto companyDetailDto = companyService.createOrUpdateCompany(company);

        return new ResponseEntity<>(companyDetailDto, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/company/{company-id}", method = RequestMethod.PUT)
    public HttpEntity<CompanyDetailDto> updateCompany(@Validated @RequestBody CompanyDto companyDto, @PathVariable(value = "company-id") BigDecimal companyId) {

        Company company = CompanyConversionUtil.mapClasses(companyDto, Company.class);
        company.setId(companyId);

        log.info("company {} ", company);

        CompanyDetailDto companyDetailDto = companyService.createOrUpdateCompany(company);

        return new ResponseEntity<>(companyDetailDto, HttpStatus.OK);

    }

}
