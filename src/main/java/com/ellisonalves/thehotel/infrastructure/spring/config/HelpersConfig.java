package com.ellisonalves.thehotel.infrastructure.spring.config;

import com.ellisonalves.thehotel.application.TimeHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class HelpersConfig {

    public static final Clock DEFAULT_CLOCK = Clock.systemUTC();

    @Bean
    TimeHelper timeHelper() {
        return new TimeHelper(DEFAULT_CLOCK);
    }

}
