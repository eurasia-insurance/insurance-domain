package com.lapsa.insurance.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.elements.InsuranceProductType;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.insurance.elements.RequestSource;
import com.lapsa.insurance.elements.TransactionProblem;
import com.lapsa.insurance.elements.TransactionStatus;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.javax.validation.NotNullValue;

@Entity
@Table(name = "INSURANCE_REQUEST")
public abstract class InsuranceRequest extends Request {

    private static final long serialVersionUID = 1L;

    public InsuranceRequest() {
    }

    public InsuranceRequest(final RequestSource source) {
	super(source);
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "RTYPE")
    @NotNullValue
    protected InsuranceRequestType type = InsuranceRequestType.UNCOMPLETE;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_STATUS")
    @NotNullValue
    protected TransactionStatus transactionStatus;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_PROBLEM")
    @NotNullValue
    protected TransactionProblem transactionProblem;

    @Basic
    @Column(name = "AGREEMENT_NUMBER")
    @NotNullValue
    protected String agreementNumber;

    @Embedded
    protected PaymentData payment = new PaymentData();

    public abstract InsuranceProduct getProduct();

    public abstract InsuranceProductType getProductType();

    // GENERATED

    public InsuranceRequestType getType() {
	return type;
    }

    public void setType(final InsuranceRequestType type) {
	this.type = type;
    }

    public TransactionStatus getTransactionStatus() {
	return transactionStatus;
    }

    public void setTransactionStatus(final TransactionStatus transactionStatus) {
	this.transactionStatus = transactionStatus;
    }

    public TransactionProblem getTransactionProblem() {
	return transactionProblem;
    }

    public void setTransactionProblem(final TransactionProblem transactionProblem) {
	this.transactionProblem = transactionProblem;
    }

    public PaymentData getPayment() {
	return payment;
    }

    public void setPayment(final PaymentData payment) {
	this.payment = payment;
    }

    public String getAgreementNumber() {
	return agreementNumber;
    }

    public void setAgreementNumber(final String agreementNumber) {
	this.agreementNumber = agreementNumber;
    }
}
