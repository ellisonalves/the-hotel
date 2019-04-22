package com.ellisonalves.thehotel.interfaces.endpoints;

import com.ellisonalves.thehotel.interfaces.dtos.CustomerDTO;
import com.ellisonalves.thehotel.interfaces.dtos.CustomerListDTO;
import com.ellisonalves.thehotel.interfaces.mapper.CustomerMapper;
import com.ellisonalves.thehotel.usecases.FindCustomersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private FindCustomersUseCase findCustomersUseCase;

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(FindCustomersUseCase findCustomersUseCase, CustomerMapper customerMapper) {
        this.findCustomersUseCase = findCustomersUseCase;
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

}
