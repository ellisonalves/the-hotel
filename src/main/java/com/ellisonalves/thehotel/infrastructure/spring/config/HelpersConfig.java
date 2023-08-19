package com.ellisonalves.thehotel.infrastructure.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ellisonalves.thehotel.application.TimeHelper;

@Configuration
public class HelpersConfig {

	@Bean
	TimeHelper timeHelper() {
		return new TimeHelper();
	}

}
