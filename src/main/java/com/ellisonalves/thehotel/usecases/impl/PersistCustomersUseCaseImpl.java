package com.ellisonalves.thehotel.usecases.impl;

import com.ellisonalves.thehotel.domain.entities.Customer;
import com.ellisonalves.thehotel.domain.repositories.CustomerRepository;
import com.ellisonalves.thehotel.usecases.PersistCustomersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
class PersistCustomersUseCaseImpl implements PersistCustomersUseCase {

    private CustomerRepository customerRepository;

    @Autowired
    public PersistCustomersUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
