package com.ellisonalves.thehotel.rest.endpoints;


import com.ellisonalves.thehotel.domain.services.GuestService;
import com.ellisonalves.thehotel.rest.interfaces.dtos.CustomerDTO;
import com.ellisonalves.thehotel.rest.interfaces.dtos.CustomerListDTO;
import com.ellisonalves.thehotel.rest.interfaces.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final GuestService guestService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(GuestService guestService, CustomerMapper customerMapper) {
        this.guestService = guestService;
        this.customerMapper = customerMapper;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerDTO find(@PathVariable Long id) {
        return customerMapper.toDTO(guestService.findOne(id).get());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CustomerListDTO find() {
        return customerMapper.toCustomerListDTO(guestService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO saved = customerMapper.toDTO(guestService.save(customerMapper.toEntity(customerDTO)));
        return ResponseEntity.created(URI.create("/customers/" + saved.getId())).body(saved);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO updated = customerMapper.toDTO(guestService.save(customerMapper.toEntity(customerDTO)));
        return ResponseEntity.ok(updated);
    }

}
