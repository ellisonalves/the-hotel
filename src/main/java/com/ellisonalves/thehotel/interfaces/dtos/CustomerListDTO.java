package com.ellisonalves.thehotel.interfaces.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomerListDTO {
    private List<CustomerDTO> customers;

    public CustomerListDTO(List<CustomerDTO> customers) {
        this.customers = customers;
    }
}
