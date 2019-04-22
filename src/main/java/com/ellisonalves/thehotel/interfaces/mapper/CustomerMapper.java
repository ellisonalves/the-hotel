package com.ellisonalves.thehotel.interfaces.mapper;

import com.ellisonalves.thehotel.domain.entities.Customer;
import com.ellisonalves.thehotel.interfaces.dtos.CustomerDTO;
import com.ellisonalves.thehotel.interfaces.dtos.CustomerListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer, CustomerDTO> {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    default CustomerListDTO toCustomerListDTO(Collection<Customer> customers) {
        return new CustomerListDTO(toDTOList(customers));
    }

}
