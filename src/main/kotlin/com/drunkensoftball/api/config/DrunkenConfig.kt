package com.drunkensoftball.api.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.jndi.JndiTemplate

import javax.sql.DataSource

@Configuration
@ComponentScan(basePackages = arrayOf("com.drunkensoftball.api"))
@PropertySource(value = ["classpath:drunken-softball-application.properties", "classpath:drunken-softball-datasource.properties"])
@EnableAutoConfiguration
open class DrunkenConfig {

    @Autowired
    lateinit var environment: Environment

    @Bean
    open fun dataSource(): DataSource {
        try {
            val jndi = JndiTemplate()
            return jndi.lookup("java:comp/env/jdbc/drunkensoftball") as DataSource
        } catch (t: Throwable) {
            logger.warn("JNDI datasource not found")
        }

        logger.warn("Using System properties for datasource")
        val factory = DataSourceBuilder
                .create()
                .driverClassName(environment.getProperty("spring.datasource.driverClassName"))
                .url(environment.getProperty("spring.datasource.url"))
                .username(environment.getProperty("spring.datasource.username"))
                .password(environment.getProperty("spring.datasource.password"))

        return factory.build()
    }

    companion object {

        val logger = LoggerFactory.getLogger(DrunkenConfig::class.java)
    }
}
