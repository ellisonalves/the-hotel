package com.ellisonalves.thehotel.interfaces.endpoints.interfaces.mapper;

import com.ellisonalves.thehotel.domain.entities.Guest;
import com.ellisonalves.thehotel.rest.interfaces.dtos.CustomerDTO;
import com.ellisonalves.thehotel.rest.interfaces.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2019-09-01T05:10:43+0200",
        comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.4 (Ubuntu)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Guest entity) {
        if (entity == null) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(entity.getId());
        customerDTO.setName(entity.getName());
        customerDTO.setDocumentNumber(entity.getDocumentNumber());
        customerDTO.setNationality(entity.getNationality());
        customerDTO.setGender(entity.getGender());
        customerDTO.setEmail(entity.getEmail());
        customerDTO.setAddress(entity.getAddress());
        customerDTO.setPhone(entity.getPhone());

        return customerDTO;
    }

    @Override
    public Guest toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        Guest guest = new Guest();

        guest.setId(dto.getId());
        guest.setName(dto.getName());
        guest.setDocumentNumber(dto.getDocumentNumber());
        guest.setNationality(dto.getNationality());
        guest.setGender(dto.getGender());
        guest.setEmail(dto.getEmail());
        guest.setAddress(dto.getAddress());
        guest.setPhone(dto.getPhone());

        return guest;
    }
}
