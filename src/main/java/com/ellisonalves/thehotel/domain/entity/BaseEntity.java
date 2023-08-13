
package com.ellisonalves.thehotel.domain.entity;

import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ID id;

    protected Instant createdAt;

    protected Long version;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public abstract ID getId();

    public abstract boolean equalTo(Object o);

    public abstract int hashCodePrime();

    @PrePersist
    void setCreateAt() {
        createdAt = Instant.now(Clock.systemUTC());
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

}