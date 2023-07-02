
package com.ellisonalves.thehotel.domain.entity;

import java.io.Serializable;

public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    public abstract ID getId();

    public abstract Long getVersion();

    public abstract boolean equalTo(Object o);

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        return equalTo(o);
    }

}