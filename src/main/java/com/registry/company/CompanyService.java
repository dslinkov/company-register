package com.registry.company;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.registry.util.CompanyConversionUtil;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

// Not using interfaces here since there is only one implementation
@Service
public class CompanyService {

    @Inject
    private CompanyRepository companyRepository;

    public List<CompanyDetailDto> getAllCompanies() {

        List<Company> compnaies = companyRepository.findAll();

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        List<CompanyDetailDto> companyDetailDtos = mapper.mapAsList(compnaies, CompanyDetailDto.class);

        return companyDetailDtos;
    }

    public CompanyDetailDto getCompany(BigDecimal companyId) {

        Optional<Company> optionalResult = companyRepository.findOne(companyId);

        if (optionalResult.isPresent()) {
            return CompanyConversionUtil.mapClasses(optionalResult.get(), CompanyDetailDto.class);
        } else {
            return null;
        }
    }

    public CompanyDetailDto createOrUpdateCompany(Company company) {

        if (company.getId() == null || company.getId().equals(BigDecimal.ZERO)) {


            company = companyRepository.insert(company);

        } else {
            company = companyRepository.update(company);
        }

        CompanyDetailDto companyDetailDto = CompanyConversionUtil.mapClasses(company, CompanyDetailDto.class);

        return companyDetailDto;
    }

}
