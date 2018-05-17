package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.java.commons.function.MyCollectors;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStreams;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "LEAD")
@HashCodePrime(3)
public class Lead extends IntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    // constructor

    protected Lead() {
    }

    // accepted

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACCEPTED")
    protected Instant acceptedOn;

    public Instant getAcceptedOn() {
	return acceptedOn;
    }

    // completed

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "COMPLETED")
    protected Instant completedOn;

    public Instant getCompletedOn() {
	return completedOn;
    }

    // closed

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CLOSED")
    protected Instant closedOn;

    public Instant getClosedOn() {
	return closedOn;
    }

    // requesterName

    @Basic
    @Column(name = "REQUESTER_NAME")
    protected String requesterName;

    public String getRequesterName() {
	return requesterName;
    }

    // phone

    @Basic
    @Column(name = "REQUESTER_PHONE")
    protected PhoneNumber requesterPhone;

    public PhoneNumber getRequesterPhone() {
	return requesterPhone;
    }

    // email

    @Basic
    @Column(name = "REQUESTER_EMAIL")
    protected String requesterEmail;

    public String getRequesterEmail() {
	return requesterEmail;
    }

    public Optional<String> optRequesterEmail() {
	return MyOptionals.of(requesterEmail);
    }

    // requesterLocale

    @Basic
    @Column(name = "REQUESTER_LOCALE")
    protected Locale requesterLocale;

    public Locale getRequesterLocale() {
	return requesterLocale;
    }

    // calculations

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "lead")
    protected List<Calculation> calculations;

    public List<Calculation> getCalculations() {
	return MyStreams.orEmptyOf(calculations)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.collect(MyCollectors.unmodifiableList());
    }

    public Optional<Calculation> optFirstCalculation() {
	return MyStreams.orEmptyOf(calculations)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.findFirst();
    }

    public boolean isHasCalculation() {
	return optFirstCalculation().isPresent();
    }

    // paymentOrders

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "lead")
    protected List<PaymentOrder> paymentOrders;

    public List<PaymentOrder> getPaymentOrders() {
	return MyStreams.orEmptyOf(paymentOrders)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.collect(MyCollectors.unmodifiableList());
    }

    public Optional<PaymentOrder> optFirstPaymentOrder() {
	return MyStreams.orEmptyOf(paymentOrders)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.findFirst();
    }

    public boolean isHasPaymentOrder() {
	return optFirstCalculation().isPresent();
    }

    void _addPaymentOrder(final PaymentOrder paymentOrder) {
	if (paymentOrders == null)
	    paymentOrders = new ArrayList<>();
	paymentOrders.add(paymentOrder);
    }

    // payments

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "lead")
    protected List<Payment> payments;

    public List<Payment> getPayments() {
	return MyStreams.orEmptyOf(payments)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.collect(MyCollectors.unmodifiableList());
    }

    public Optional<Payment> optFirstPayment() {
	return MyStreams.orEmptyOf(payments)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.findFirst();
    }

    public boolean isHasPayment() {
	return optFirstCalculation().isPresent();
    }

    void _addPayment(final Payment payment) {
	if (payments == null)
	    payments = new ArrayList<>();
	payments.add(payment);
    }

    // contracts

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "lead")
    protected List<Contract> contracts;

    public List<Contract> getContracts() {
	return MyStreams.orEmptyOf(contracts)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.collect(MyCollectors.unmodifiableList());
    }

    public Optional<Contract> optFirstContract() {
	return MyStreams.orEmptyOf(contracts)
		.sorted(CreationParameteredIntIdEntitySuperclass.CREATION_REVERSE_ORDER_COMPARATOR)
		.findFirst();
    }

    public boolean isHasContract() {
	return optFirstCalculation().isPresent();
    }

    void _addContract(final Contract contract) {
	if (contracts == null)
	    contracts = new ArrayList<>();
	contracts.add(contract);
    }
}
