package com.ellisonalves.thehotel.infrastructure.spring.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan("com.ellisonalves.thehotel.domain.entity")
public class JpaRepositoryTestConfig {

}