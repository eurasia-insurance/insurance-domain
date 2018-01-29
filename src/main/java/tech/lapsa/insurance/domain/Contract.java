package tech.lapsa.insurance.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "CONTRACT")
@HashCodePrime(29)
public class Contract extends LeadAttachement {

    private static final long serialVersionUID = 1L;

    // constructor

    protected Contract() {
    }

    //

    @Basic
    @Column(name = "NUMBER")
    protected String number;

    public String getNumber() {
	return number;
    }

    // amount

    @Embedded
    protected Amount amount;

    public Amount getAmount() {
	return amount;
    }

}
