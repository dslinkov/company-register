package com.registry.employee;

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
public class EmployeeRepository {

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

    public Employee insert(Employee employee) {

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(employee);

        jdbcDaoSupport.getNamedParameterJdbcTemplate().update(
                "insert into employee (first_name, last_name, position, company_id) values (:firstName, :lastName, :position, :companyId)",
                parameters, generatedKeyHolder, new String[] { "id" });

        employee.setId(new BigDecimal(generatedKeyHolder.getKey().toString()));

        return employee;
    }

    public Employee update(Employee employee) {

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(employee);

        jdbcDaoSupport.getNamedParameterJdbcTemplate().update("update employee set first_name = :firstName, last_name = :lastName, position = :position, company_id = :companyId "
                + " where id = :id", parameters);

        return employee;
    }

    public List<Employee> findAll(BigDecimal companyId) {

        SqlParameterSource parameters = new MapSqlParameterSource("companyId", companyId);

        return jdbcDaoSupport.getNamedParameterJdbcTemplate().query("select id, first_name, last_name, position, company_id from employee where company_id = :companyId order by id", parameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
    }

    public Optional<Employee> findOne(BigDecimal id) {

        SqlParameterSource parameters = new MapSqlParameterSource("id", id);

        List<Employee> results = jdbcDaoSupport.getNamedParameterJdbcTemplate().query(
                "id, first_name, last_name, position, company_id from employee where id = :id", parameters,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return LocalDataAccessUtils.optional(results);
    }

}
