package com.ellisonalves.thehotel.usecases.impl;

import com.ellisonalves.thehotel.domain.entities.Customer;
import com.ellisonalves.thehotel.domain.repositories.CustomerRepository;
import com.ellisonalves.thehotel.usecases.FindCustomersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class FindCustomersUseCaseImpl implements FindCustomersUseCase {

    private CustomerRepository customerRepository;

    @Autowired
    public FindCustomersUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findOne(Long id) {
        return customerRepository.findById(id);
    }

}
