package com.bastagruppen.springskolsystem.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.TimeZone;

import static java.time.Clock.systemUTC;
import static java.util.TimeZone.getTimeZone;

@Configuration
public class TimeConfig {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(getTimeZone("UTC"));
    }

    @Bean
    public Clock clock() {
        return systemUTC();
    }
}