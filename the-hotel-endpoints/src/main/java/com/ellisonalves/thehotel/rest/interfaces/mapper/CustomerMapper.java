package com.ellisonalves.thehotel.rest.interfaces.mapper;

import com.ellisonalves.thehotel.domain.entities.Guest;
import com.ellisonalves.thehotel.rest.interfaces.dtos.CustomerDTO;
import com.ellisonalves.thehotel.rest.interfaces.dtos.CustomerListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface CustomerMapper extends BaseMapper<Guest, CustomerDTO> {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    default CustomerListDTO toCustomerListDTO(Collection<Guest> guests) {
        return new CustomerListDTO(toDTOList(guests));
    }

}
