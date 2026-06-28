package com.dreamstock.infrastructure.config;

import com.dreamstock.infrastructure.datasource.DataSourceType;
import com.dreamstock.infrastructure.datasource.DynamicRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public HikariDataSource mysqlDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.clickhouse")
    public HikariDataSource clickhouseDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MYSQL, mysqlDataSource());
        targetDataSources.put(DataSourceType.CLICKHOUSE, clickhouseDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(mysqlDataSource());
        return dynamicDataSource;
    }
}
