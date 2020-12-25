package com.mrtn.fx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${fxdb.url}")
    String url;

    @Value("${fxdb.driverClassName}")
    String driverClassName;

    @Value("${fxdb.username}")
    String userName;

    @Value("${fxdb.password}")
    String password;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .driverClassName(driverClassName)
                .username(userName)
                .password(password)
                .build();
    }

}
