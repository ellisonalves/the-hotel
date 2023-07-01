package com.ellisonalves.thehotel.infrastructure.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ellisonalves.thehotel.infrastructure.jpa.repository.BookingRepository;

//@DataJpaTest
public class BookingRepositoryITCase {

    @Autowired
    private BookingRepository bookingRepository;

//    @Test
    public void shouldExistBook() {
        //assertThat(bookingRepository.findAll()).isNotNull();
    }
}