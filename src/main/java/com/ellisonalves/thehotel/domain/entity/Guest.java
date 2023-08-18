package com.ellisonalves.thehotel.domain.entity;

import java.util.Objects;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.aggregates.GenderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Guest extends BaseEntity<UUID> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String documentNumber;

	@Column(nullable = false)
	private String name;
	private String nationality;
	
	@Enumerated(EnumType.STRING)
	private GenderType genderType;
	private String email;
	private String address;
	private String phone;

	@Version
	private Long version;

	@Override
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

	@Enumerated(EnumType.STRING)
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
	public boolean equalTo(Object o) {
		Guest other = (Guest) o;
		return Objects.equals(this.getId(), other.getId());
	}

	@Override
	public int hashCodePrime() {
		return 37;
	}

	@Override
	public Long getVersion() {
		return version;
	}

}
