package com.ellisonalves.thehotel.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.ellisonalves.thehotel.infrastructure.mappers.GuestMapper;
import com.ellisonalves.thehotel.infrastructure.mappers.GuestMapperImpl;

@TestConfiguration
@ComponentScan("com.ellisonalves.thehotel.infrastructure.jpa")
public class JpaRepositoryTestConfig {

    @Bean
    public GuestMapper guestMapper() {
        return new GuestMapperImpl();
    }
}
