package com.ellisonalves.thehotel.rest.interfaces.dtos;

import com.ellisonalves.thehotel.domain.types.GenderType;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String documentNumber;
    private String nationality;
    private GenderType gender;
    private String email;
    private String address;
    private String phone;
}
