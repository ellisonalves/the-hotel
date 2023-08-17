package com.ellisonalves.thehotel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.ellisonalves.thehotel.infrastructure.spring.config.JpaRepositoryTestConfig;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@IntegrationTest
@DataJpaTest
@Import(JpaRepositoryTestConfig.class)
public @interface DatabaseTest {
    
}
