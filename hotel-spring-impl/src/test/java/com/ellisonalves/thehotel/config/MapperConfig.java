package com.ellisonalves.thehotel.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.ellisonalves.thehotel.infrastructure.controller.RoomMapper;

@TestConfiguration
public class MapperConfig {

    @Bean
    public RoomMapper roomMapper() {
        return RoomMapper.INSTANCE;
    }

}
