package com.ellisonalves.thehotel.domain;

import java.io.Serializable;

public interface IEntity<ID extends Serializable> {

	ID getId();

    boolean equalTo(Object o);

    int hashCodePrime();
	
}