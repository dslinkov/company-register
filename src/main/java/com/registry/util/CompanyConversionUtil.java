package com.registry.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class CompanyConversionUtil {

    public static <S, D>  D mapClasses(S dto, Class<D> cls) {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        D entity = mapper.map(dto, cls);

        return entity;
    }

}
