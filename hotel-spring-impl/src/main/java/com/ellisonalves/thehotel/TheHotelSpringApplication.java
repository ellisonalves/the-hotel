package com.ellisonalves.thehotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ellisonalves.thehotel.infrastructure")
public class TheHotelSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheHotelSpringApplication.class, args);
    }

}
