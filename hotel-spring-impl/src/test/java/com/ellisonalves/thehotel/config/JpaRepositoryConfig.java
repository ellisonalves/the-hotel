package com.ellisonalves.thehotel.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@TestConfiguration
@ComponentScan("com.ellisonalves.thehotel.infrastructure.jpa")
public class JpaRepositoryConfig {
}
