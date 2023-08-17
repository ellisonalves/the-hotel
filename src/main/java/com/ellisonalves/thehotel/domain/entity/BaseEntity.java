
package com.ellisonalves.thehotel.domain.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Instant createdAt;

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		return equalTo(o);
	}

	@Override
	public int hashCode() {
		final int prime = hashCodePrime();
		int result = super.hashCode();
		ID id = getId();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return Objects.hashCode(id);
	}

	public abstract ID getId();

	public abstract Long getVersion();

	public abstract boolean equalTo(Object o);

	public abstract int hashCodePrime();

}