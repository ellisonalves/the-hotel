package com.ellisonalves.thehotel.infrastructure.spring.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan("com.ellisonalves.thehotel.infrastructure.spring.jpa")
public class JpaRepositoryTestConfig {

}
