package com.ellisonalves.thehotel.usecases;

import com.ellisonalves.thehotel.domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface FindCustomersUseCase {

    List<Customer> findAll();

    Optional<Customer> findOne(Long id);

}
