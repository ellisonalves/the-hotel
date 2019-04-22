package com.ellisonalves.thehotel.usecases;

import com.ellisonalves.thehotel.domain.entities.Customer;
import com.ellisonalves.thehotel.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindCustomersUseCaseImpl implements FindCustomersUseCase {

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
