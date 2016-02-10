package com.registry.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.addDataSourceProperty("url", "jdbc:postgresql://ec2-54-225-223-40.compute-1.amazonaws.com:5432/dc1jif337ap365?sslmode=require");
        config.addDataSourceProperty("user", "bitlvzkeswwxad");
        config.addDataSourceProperty("password", "bbSAJrt6D4S8rntrm-rJKhKIfr");
        //config.setJdbcUrl("jdbc://ec2-54-225-223-40.compute-1.amazonaws.com:5432/dc1jif337ap365");



        return new HikariDataSource(config);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(final DataSource dataSource) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;

    }
}
