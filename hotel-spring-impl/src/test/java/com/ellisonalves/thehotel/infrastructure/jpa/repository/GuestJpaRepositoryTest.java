package com.ellisonalves.thehotel.infrastructure.jpa.repository;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.ellisonalves.thehotel.config.JpaRepositoryTestConfig;
import com.ellisonalves.thehotel.domain.aggregates.GenderType;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.GuestJpa;

@DataJpaTest
@Import(JpaRepositoryTestConfig.class)
public class GuestJpaRepositoryTest {

    @Autowired
    private GuestJpaRepository repository;

    @Test
    public void test() {
        GuestJpa guest = new GuestJpa();
        guest.setId(UUID.randomUUID());
        guest.setAddress("123");
        guest.setDocumentNumber("123");
        guest.setEmail("email");
        guest.setGenderType(GenderType.FEMALE);
        guest.setName("nameee");
        guest.setNationality("br");
        guest.setPhone("123098");

        repository.save(guest);
    }

}
