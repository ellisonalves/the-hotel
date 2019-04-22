package com.ellisonalves.thehotel.domain.repositories;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryITCase {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldExistBook() {
        Assert.assertThat(bookRepository.findAll(), CoreMatchers.is(CoreMatchers.notNullValue()));
    }
}