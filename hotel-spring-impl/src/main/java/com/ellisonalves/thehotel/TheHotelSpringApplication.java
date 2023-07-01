package com.ellisonalves.thehotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication //(scanBasePackages = "com.ellisonalves.thehotel")
// @ComponentScan("com.ellisonalves.thehotel")
// @EntityScan(basePackages ="com.ellisonalves.thehotel.infrastructure.jpa.entity")
// @EnableJpaRepositories(basePackages ="com.ellisonalves.thehotel.infrastructure.jpa.repository")
// @EnableAutoConfiguration
public class TheHotelSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheHotelSpringApplication.class, args);
    }

}
