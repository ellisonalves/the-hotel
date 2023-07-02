package com.ellisonalves.thehotel.infrastructure.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

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

    private UUID id = UUID.randomUUID();

    @Test
    public void shouldPersistAndFindGuest() {
        var id = UUID.randomUUID();

        persistGuest(id);

        assertThat(repository.findById(id)).isPresent();
    }

    @Test
    public void shouldFindNoGuest() {
        var id = UUID.randomUUID();
        assertThat(repository.findById(id)).isNotPresent();
    }

    @Test
    public void shouldDeleteGuest() {
        persistGuest(id);

        repository.delete(id);

        assertThat(repository.findById(id)).isNotPresent();
    }

    @Test
    public void shouldFindAllWithNoGuest() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void shouldFindAllGuests() {
        persistGuest(UUID.randomUUID());
        persistGuest(UUID.randomUUID());
        persistGuest(UUID.randomUUID());
        assertThat(repository.findAll()).hasSize(3);
    }

    private void persistGuest(UUID id) {
        GuestJpa guest = new GuestJpa();
        guest.setId(id);
        guest.setAddress("123");
        guest.setDocumentNumber("123");
        guest.setEmail("email");
        guest.setGenderType(GenderType.FEMALE);
        guest.setName("nameee");
        guest.setNationality("br");
        guest.setPhone("123098");

        repository.persist(guest);
    }

}
