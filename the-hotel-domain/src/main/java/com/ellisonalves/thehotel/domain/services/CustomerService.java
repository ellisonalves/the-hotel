package com.ellisonalves.thehotel.domain.services;

import com.ellisonalves.thehotel.domain.entities.Customer;

import java.util.List;

public interface CustomerService extends CRUDService<Customer, Long> {
    List<Customer> findAll();
}
