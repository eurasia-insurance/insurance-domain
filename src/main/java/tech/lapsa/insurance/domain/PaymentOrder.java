package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import java.time.Instant;
import java.util.Currency;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tech.lapsa.java.commons.function.MyExceptions;
import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "PAYMENT_ORDER")
@HashCodePrime(19)
public class PaymentOrder extends LeadAttachement {

    private static final long serialVersionUID = 1L;

    // constructor

    protected PaymentOrder() {
    }

    // amount

    @Embedded
    protected Amount amount;

    public Amount getAmount() {
	return amount;
    }

    // payment

    @OneToOne(fetch = EAGER, cascade = ALL, orphanRemoval = false, mappedBy = "order")
    @JoinColumn(name = "PAYMENT_ID")
    protected Payment payment;

    public Payment getPayment() {
	return payment;
    }

    public boolean isPaid() {
	return payment != null;
    }

    // canceled

    @Basic
    @Column(name = "CANCELED")
    protected Instant canceledOn;

    public Instant getCanceledOn() {
	return canceledOn;
    }

    public boolean isCanceled() {
	return canceledOn != null;
    }

    // reference

    @Basic
    @Column(name = "REFERENCE")
    protected String reference;

    public String getReference() {
	return reference;
    }

    // pending

    public boolean isPending() {
	return !(isCanceled() || isPaid());
    }

    public PaymentOrder requirePending() throws IllegalStateException {
	if (!isPending())
	    throw MyExceptions.illegalStateFormat("Is not pending %1$s", this);
	return this;
    }

    // controls

    public PaymentBuilder paying() throws IllegalStateException {
	requirePending();
	return new PaymentBuilder();
    }

    public final class PaymentBuilder {

	private PaymentBuilder() {
	}

	private String source;
	private Double amount;
	private Currency currency;
	private Instant confirmedOn;
	private String reference;

	public PaymentBuilder withSource(final String source) throws IllegalArgumentException {
	    this.source = MyStrings.requireNonEmpty(source, "source");
	    return this;
	}

	public PaymentBuilder withAmount(final Double amount, final Currency currency) throws IllegalArgumentException {
	    this.amount = MyNumbers.requirePositive(amount, "amount");
	    this.currency = MyObjects.requireNonNull(currency, "currency");
	    return this;
	}

	public PaymentBuilder withConfirmedOn(final Instant confirmedOn) throws IllegalArgumentException {
	    this.confirmedOn = MyObjects.requireNonNull(confirmedOn, "confirmedOn");
	    return this;
	}

	public PaymentBuilder withReference(final String reference) throws IllegalArgumentException {
	    this.reference = MyStrings.requireNonEmpty(reference, "reference");
	    return this;
	}

	public Payment complete() throws IllegalArgumentException, IllegalStateException {
	    requirePending();
	    final Payment res = new Payment();

	    res.source = MyStrings.requireNonEmpty(source, "source");
	    res.amount = new Amount();
	    res.amount.amount = MyNumbers.requirePositive(amount, "amount");
	    res.amount.currency = MyObjects.requireNonNull(currency, "currency");
	    res.confirmedOn = confirmedOn;
	    res.reference = reference;

	    res.order = PaymentOrder.this;
	    payment = res;

	    res.lead = lead;
	    lead._addPayment(res);

	    return res;
	}
    }
}
