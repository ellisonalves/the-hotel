package com.ellisonalves.thehotel.domain.repositories;

import com.ellisonalves.thehotel.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
