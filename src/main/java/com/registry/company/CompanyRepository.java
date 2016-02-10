package com.registry.company;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.registry.util.LocalDataAccessUtils;

@Repository
public class CompanyRepository {

    private NamedParameterJdbcDaoSupport jdbcDaoSupport;
    // private DataSource dataSource;

    @Inject
    public void setDataSource(DataSource dataSource) {

        // this.dataSource = dataSource;
        this.jdbcDaoSupport = new NamedParameterJdbcDaoSupport();
        this.jdbcDaoSupport.setDataSource(dataSource);
    }

    // set name = :name, phone = :phone, email = :email, address = :address,
    // city = :city, country = :country

    public Company insert(Company entity) {

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(entity);

        jdbcDaoSupport.getNamedParameterJdbcTemplate().update(
                "insert into company (name, phone, email, address, city, country) " + " values (:name, :phone, :email, :address, :city, :country)",
                parameters, generatedKeyHolder, new String[] { "id" });

        entity.setId(new BigDecimal(generatedKeyHolder.getKey().toString()));

        return entity;
    }

    public Company update(Company company) {

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(company);

        jdbcDaoSupport.getNamedParameterJdbcTemplate().update("update company set name = :name, phone = :phone, email = :email, address = :address, "
                + " city = :city, country = :country where id = :id", parameters);

        return company;
    }

    public List<Company> findAll() {

        return jdbcDaoSupport.getJdbcTemplate().query("select id, name, phone, email, address, city, country from company order by id",
                BeanPropertyRowMapper.newInstance(Company.class));
    }

    public Optional<Company> findOne(BigDecimal id) {

        SqlParameterSource parameters = new MapSqlParameterSource("id", id);

        List<Company> results = jdbcDaoSupport.getNamedParameterJdbcTemplate().query(
                "select id, name, phone, email, address, city, country from company where id = :id", parameters,
                BeanPropertyRowMapper.newInstance(Company.class));

        return LocalDataAccessUtils.optional(results);
    }

}
