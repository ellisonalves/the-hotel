package com.ellisonalves.thehotel.domain.services.impl;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.domain.entities.Customer;
import com.ellisonalves.thehotel.domain.repositories.CustomerRepository;
import com.ellisonalves.thehotel.domain.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findOne(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

}
