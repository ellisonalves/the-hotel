package com.ellisonalves.thehotel.infrastructure.jpa.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.ellisonalves.thehotel.domain.aggregates.GenderType;
import com.ellisonalves.thehotel.domain.entity.Guest;

@Entity
public class GuestJpa extends Guest {

    private UUID id;

    private String name;

    private String documentNumber;

    private String nationality;

    private GenderType gender;

    private String email;

    private String address;

    private String phone;

    @Override
    public String getAddress() {
        // TODO Auto-generated method stub
        return super.getAddress();
    }

    @Override
    public String getDocumentNumber() {
        // TODO Auto-generated method stub
        return super.getDocumentNumber();
    }

    @Override
    public String getEmail() {
        // TODO Auto-generated method stub
        return super.getEmail();
    }

    @Enumerated(EnumType.STRING)
    @Override
    public GenderType getGenderType() {
        // TODO Auto-generated method stub
        return super.getGenderType();
    }

    @Id
    @Override
    public UUID getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public String getNationality() {
        // TODO Auto-generated method stub
        return super.getNationality();
    }

    @Override
    public String getPhone() {
        // TODO Auto-generated method stub
        return super.getPhone();
    }

    @Override
    public void setAddress(String address) {
        // TODO Auto-generated method stub
        super.setAddress(address);
    }

    @Override
    public void setDocumentNumber(String documentNumber) {
        // TODO Auto-generated method stub
        super.setDocumentNumber(documentNumber);
    }

    @Override
    public void setEmail(String email) {
        // TODO Auto-generated method stub
        super.setEmail(email);
    }

    @Override
    public void setGenderType(GenderType genderType) {
        // TODO Auto-generated method stub
        super.setGenderType(genderType);
    }

    @Override
    public void setId(UUID id) {
        // TODO Auto-generated method stub
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name);
    }

    @Override
    public void setNationality(String nationality) {
        // TODO Auto-generated method stub
        super.setNationality(nationality);
    }

    @Override
    public void setPhone(String phone) {
        // TODO Auto-generated method stub
        super.setPhone(phone);
    }

}
