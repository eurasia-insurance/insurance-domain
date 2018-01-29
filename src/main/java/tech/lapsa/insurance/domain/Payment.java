package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import java.time.Instant;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "PAYMENT")
@HashCodePrime(23)
public class Payment extends LeadAttachement {

    private static final long serialVersionUID = 1L;

    // constructor

    protected Payment() {
    }

    // source

    @Basic
    @Column(name = "SOURCE")
    protected String source;

    public String getSource() {
	return source;
    }

    // amount

    @Embedded
    protected Amount amount;

    public Amount getAmount() {
	return amount;
    }

    // confirmationRequired

    @Basic
    @Column(name = "CONFIRMED")
    protected Instant confirmedOn;

    public Instant getConfirmedOn() {
	return confirmedOn;
    }

    public boolean isPendingConfirmation() {
	return confirmedOn == null;
    }

    public Payment requirePendingConfirmation() throws IllegalStateException {
	if (!isPendingConfirmation())
	    throw MyExceptions.illegalStateFormat("Confirmation is not pending or is already confirmed");
	return this;
    }

    // order

    @OneToOne(fetch = EAGER, cascade = ALL, orphanRemoval = false)
    protected PaymentOrder order;

    public PaymentOrder getOrder() {
	return order;
    }

    // reference

    @Basic
    @Column(name = "REFERENCE")
    protected String reference;

    public String getReference() {
	return reference;
    }

    // controls

    public Payment confirming() throws IllegalStateException {
	return confirming(Instant.now());
    }

    public Payment confirming(final Instant at) throws IllegalStateException {
	requirePendingConfirmation();
	this.confirmedOn = MyObjects.requireNonNull(at);
	return this;
    }
}
