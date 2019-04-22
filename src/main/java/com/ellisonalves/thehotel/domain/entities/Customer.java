package com.ellisonalves.thehotel.domain.entities;

import com.ellisonalves.thehotel.domain.types.GenderType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String documentNumber;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String email;
    private String address;
    private String phone;
}
