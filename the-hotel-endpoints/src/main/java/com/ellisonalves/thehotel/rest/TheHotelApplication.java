package com.ellisonalves.thehotel.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ellisonalves.thehotel.*")
public class TheHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheHotelApplication.class, args);
    }

}
