package com.ellisonalves.thehotel.infrastructure.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ellisonalves.thehotel.infrastructure.jpa.repository.GuestJpaRepository;

//@DataJpaTest
public class GuestRepositoryITCase {

//    @Autowired
    private GuestJpaRepository guestRepository;

  //  @Test
    public void shouldExistCustomer() {
        // assertThat(guestRepository.findAll()).isNotNull();
    }
}