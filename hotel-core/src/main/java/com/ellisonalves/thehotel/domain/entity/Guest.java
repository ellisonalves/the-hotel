package com.ellisonalves.thehotel.domain.entity;

import java.util.Objects;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.GenderType;

public class Guest extends BaseEntity<UUID> {

    private UUID id;
    private String documentNumber;
    private String name;
    private String nationality;
    private GenderType genderType;
    private String email;
    private String address;
    private String phone;
    private Long version;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equalTo(Object o) {
        Guest other = (Guest) o;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCodePrime() {
        return 37;
    }

}
