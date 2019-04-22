package com.ellisonalves.thehotel.usecases;

import com.ellisonalves.thehotel.domain.entities.Customer;

public interface PersistCustomersUseCase {
    Customer save(Customer customer);
}
