package com.lapsa.insurance.jpa;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSURANCE_VER_2_4")
@Access(AccessType.PROPERTY)
public class InsuranceVersion {

    @Id
    public int getDummy() {
	return 0;
    }

    public void setDummy(final int dummy) {
    }
}
