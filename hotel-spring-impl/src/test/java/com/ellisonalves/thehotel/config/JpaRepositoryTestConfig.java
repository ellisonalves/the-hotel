package com.ellisonalves.thehotel.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.ellisonalves.thehotel.infrastructure.jpa.mappers.GuestJpaModelMapper;
import com.ellisonalves.thehotel.infrastructure.jpa.mappers.GuestJpaModelMapperImpl;

@TestConfiguration
@ComponentScan("com.ellisonalves.thehotel.infrastructure.jpa")
public class JpaRepositoryTestConfig {

    @Bean
    public GuestJpaModelMapper guestMapper() {
        return new GuestJpaModelMapperImpl();
    }

}
