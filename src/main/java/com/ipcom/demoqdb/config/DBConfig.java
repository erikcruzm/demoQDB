package com.ipcom.demoqdb.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by z001qgd on 1/17/18.
 */
@Configuration
public class DBConfig {

	@Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource dataSource() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }
}
