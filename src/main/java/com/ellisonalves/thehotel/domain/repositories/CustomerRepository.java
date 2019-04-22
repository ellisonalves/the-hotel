package com.ellisonalves.thehotel.domain.repositories;

import com.ellisonalves.thehotel.domain.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
