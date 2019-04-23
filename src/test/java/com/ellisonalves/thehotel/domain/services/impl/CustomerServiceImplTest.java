package com.ellisonalves.thehotel.domain.services.impl;

import com.ellisonalves.thehotel.domain.entities.Customer;
import com.ellisonalves.thehotel.domain.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    private static final long CUSTOMER_ID = 1L;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void saveSuccessfully() {
        Customer customer = new Customer();
        customerService.save(customer);

        verify(customerRepository).save(Mockito.any());
    }

    @Test
    public void findAllSuccessfully() {
        customerService.findAll();

        verify(customerRepository).findAll();
    }

    @Test(expected = RuntimeException.class)
    public void findOneThrowsExceptionWhenEntityNotFound() {
        customerService.findOne(CUSTOMER_ID);
        verify(customerRepository).findById(CUSTOMER_ID);
    }

    @Test
    public void findOneSuccessfully() {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);

        Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));

        customerService.findOne(CUSTOMER_ID);
        verify(customerRepository).findById(CUSTOMER_ID);
    }

    @Test
    public void deleteSuccessfully() {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customerService.delete(customer);

        verify(customerRepository).delete(customer);
    }
}