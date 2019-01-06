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
import com.lapsa.insurance.elements.InsuranceRequestCancellationReason;
import com.lapsa.insurance.elements.InsuranceRequestStatus;

@Entity
@Table(name = "INSURANCE_REQUEST")
public abstract class InsuranceRequest extends Request {

    private static final long serialVersionUID = 1L;

    public InsuranceRequest() {
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "RTYPE")
    protected InsuranceRequestType type = InsuranceRequestType.UNCOMPLETE;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "INSURANCE_REQUEST_STATUS")
    protected InsuranceRequestStatus insuranceRequestStatus;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "INSURANCE_REQUEST_CANCELLATION_REASON")
    protected InsuranceRequestCancellationReason insuranceRequestCancellationReason;

    @Basic
    @Column(name = "AGREEMENT_NUMBER")
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

    public InsuranceRequestStatus getInsuranceRequestStatus() {
	return insuranceRequestStatus;
    }

    public void setInsuranceRequestStatus(final InsuranceRequestStatus insuranceRequestStatus) {
	this.insuranceRequestStatus = insuranceRequestStatus;
    }

    public InsuranceRequestCancellationReason getInsuranceRequestCancellationReason() {
	return insuranceRequestCancellationReason;
    }

    public void setInsuranceRequestCancellationReason(final InsuranceRequestCancellationReason insuranceRequestCancellationReason) {
	this.insuranceRequestCancellationReason = insuranceRequestCancellationReason;
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
