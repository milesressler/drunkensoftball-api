package com.drunkensoftball.api;

import com.drunkensoftball.api.config.DrunkenConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;

@SpringBootApplication
@Import(DrunkenConfig.class)
public class DrunkenBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(DrunkenBootApplication.class, args);
    }
}
