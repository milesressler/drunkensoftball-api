package com.drunkensoftball.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.drunkensoftball.api"})
@PropertySource(value = "classpath:drunken-softball-application.properties")
@EnableAutoConfiguration
public class DrunkenConfig {

    public static final Logger logger = LoggerFactory.getLogger(DrunkenConfig.class);

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        try {
            JndiTemplate jndi = new JndiTemplate();
            return (DataSource) jndi.lookup("java:comp/env/jdbc/drunkensoftball");
        } catch (Throwable t) {
            logger.warn("JNDI datasource not found");
        }

        logger.warn("Using System properties for datasource");
        DataSourceBuilder factory = DataSourceBuilder
                .create()
                .driverClassName(environment.getProperty("spring.datasource.driverClassName"))
                .url(environment.getProperty("spring.datasource.url"))
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password"));

        return factory.build();
    }
}
