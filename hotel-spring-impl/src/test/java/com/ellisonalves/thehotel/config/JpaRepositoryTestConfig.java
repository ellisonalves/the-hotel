package com.ellisonalves.thehotel.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan("com.ellisonalves.thehotel.infrastructure.jpa")
public class JpaRepositoryTestConfig {

    // @Bean
    // public GuestJpaModelMapper guestMapper() {
    // return new GuestJpaModelMapperImpl();
    // }

    // @Bean
    // public BookingJpaModelMapper bookingJpaModelMapper() {
    // return new BookingJpaModelMapperImpl();
    // }

}
