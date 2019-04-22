package com.ellisonalves.thehotel.interfaces.endpoints;


import com.ellisonalves.thehotel.interfaces.dtos.CustomerDTO;
import com.ellisonalves.thehotel.interfaces.dtos.CustomerListDTO;
import com.ellisonalves.thehotel.interfaces.mapper.CustomerMapper;
import com.ellisonalves.thehotel.usecases.FindCustomersUseCase;
import com.ellisonalves.thehotel.usecases.PersistCustomersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private FindCustomersUseCase findCustomersUseCase;

    private PersistCustomersUseCase persitCustomersUseCase;

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(FindCustomersUseCase findCustomersUseCase, PersistCustomersUseCase persitCustomersUseCase, CustomerMapper customerMapper) {
        this.findCustomersUseCase = findCustomersUseCase;
        this.persitCustomersUseCase = persitCustomersUseCase;
        this.customerMapper = customerMapper;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerDTO find(@PathVariable Long id) {
        return customerMapper.toDTO(findCustomersUseCase.findOne(id).orElseThrow(() -> new RuntimeException("User Not Found!")));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerListDTO find() {
        return customerMapper.toCustomerListDTO(findCustomersUseCase.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO saved = customerMapper.toDTO(persitCustomersUseCase.save(customerMapper.toEntity(customerDTO)));
        return ResponseEntity.created(URI.create("/customers/" + saved.getId())).body(saved);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO updated = customerMapper.toDTO(persitCustomersUseCase.save(customerMapper.toEntity(customerDTO)));
        return ResponseEntity.ok(updated);
    }

}
