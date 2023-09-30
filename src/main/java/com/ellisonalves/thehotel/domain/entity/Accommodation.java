package com.ellisonalves.thehotel.domain.entity;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;

import com.ellisonalves.thehotel.domain.BaseEntity;
import com.ellisonalves.thehotel.domain.aggregates.AccommodationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Accommodation extends BaseEntity<UUID> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String doorNumber;

	@Enumerated(EnumType.STRING)
	private AccommodationType accommodationType;
	private Currency currency;
	private BigDecimal amount;
	private Long version;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public AccommodationType getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(AccommodationType accommodationType) {
		this.accommodationType = accommodationType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public boolean equalTo(Object o) {
		Accommodation other = (Accommodation) o;
		return Objects.equals(this.getId(), other.getId());
	}

	@Override
	public int hashCodePrime() {
		return 41;
	}

	@Override
	public Long getVersion() {
		return version;
	}

}
