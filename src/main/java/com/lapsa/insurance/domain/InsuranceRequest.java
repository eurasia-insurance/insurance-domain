package com.lapsa.insurance.domain;

import java.time.Instant;
import java.util.Arrays;
import java.util.Currency;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.elements.InsuranceProductType;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.kz.taxpayer.TaxpayerNumber;

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

    @Basic
    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Basic
    @Column(name = "PAYMENT_REFERENCE")
    private String reference;

    @Basic
    @Column(name = "PAYMENT_INSTANT")
    private Instant instant;

    @Basic
    @Column(name = "PAYMENT_METHOD_NAME")
    private String methodName;

    @Basic
    @Column(name = "PAYER_NAME")
    private String payerName;

    @Basic
    @Column(name = "PAYMENT_CARD")
    private String card;

    @Basic
    @Column(name = "PAYMENT_CARD_BANK")
    private String cardBank;

    @Basic
    @Column(name = "PAYMENT_AMOUNT")
    private Double amount;

    @Basic
    @Column(name = "PAYMENT_CURRENCY")
    private Currency currency;

    @Basic
    @Column(name = "INVOICE_PRODUCT_NAME")
    private String invoiceProductName;

    @Basic
    @Column(name = "INVOICE_QUANTITY")
    private Integer invoiceQuantity;

    @Basic
    @Column(name = "INVOICE_AMOUNT")
    private Double invoiceAmount;

    @Basic
    @Column(name = "INVOICE_CURRENCY")
    private Currency invoiceCurrency;

    @Basic
    @Column(name = "INVOICE_PAYEE_NAME")
    private String invoicePayeeName;

    @Basic
    @Column(name = "INVOICE_PAYEE_EMAIL")
    private String invoicePayeeEmail;

    @Basic
    @Column(name = "INVOICE_PAYEE_PHONE")
    private PhoneNumber invoicePayeePhone;

    @Basic
    @Column(name = "INVOICE_PAYEE_TAXPAYER_NUMBER")
    private TaxpayerNumber invoicePayeeTaxpayerNumber;

    @Basic
    @Column(name = "INVOICE_LANGUAGE")
    private LocalizationLanguage invoiceLanguage;

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
    
    public boolean insuranceRequestStatusIn(InsuranceRequestStatus... statuses) {
	return Arrays.stream(statuses)
		.anyMatch(it -> it.equals(getInsuranceRequestStatus()));
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

    public String getInvoiceNumber() {
	return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
    }

    public String getReference() {
	return reference;
    }

    public void setReference(String reference) {
	this.reference = reference;
    }

    public Instant getInstant() {
	return instant;
    }

    public void setInstant(Instant instant) {
	this.instant = instant;
    }

    public String getMethodName() {
	return methodName;
    }

    public void setMethodName(String methodName) {
	this.methodName = methodName;
    }

    public String getPayerName() {
	return payerName;
    }

    public void setPayerName(String payerName) {
	this.payerName = payerName;
    }

    public String getCard() {
	return card;
    }

    public void setCard(String card) {
	this.card = card;
    }

    public String getCardBank() {
	return cardBank;
    }

    public void setCardBank(String cardBank) {
	this.cardBank = cardBank;
    }

    public Double getAmount() {
	return amount;
    }

    public void setAmount(Double amount) {
	this.amount = amount;
    }

    public Currency getCurrency() {
	return currency;
    }

    public void setCurrency(Currency currency) {
	this.currency = currency;
    }

    public String getInvoiceProductName() {
	return invoiceProductName;
    }

    public void setInvoiceProductName(String invoiceProductName) {
	this.invoiceProductName = invoiceProductName;
    }

    public Integer getInvoiceQuantity() {
	return invoiceQuantity;
    }

    public void setInvoiceQuantity(Integer invoiceQuantity) {
	this.invoiceQuantity = invoiceQuantity;
    }

    public Double getInvoiceAmount() {
	return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
	this.invoiceAmount = invoiceAmount;
    }

    public Currency getInvoiceCurrency() {
	return invoiceCurrency;
    }

    public void setInvoiceCurrency(Currency invoiceCurrency) {
	this.invoiceCurrency = invoiceCurrency;
    }

    public String getInvoicePayeeName() {
	return invoicePayeeName;
    }

    public void setInvoicePayeeName(String invoicePayeeName) {
	this.invoicePayeeName = invoicePayeeName;
    }

    public String getInvoicePayeeEmail() {
	return invoicePayeeEmail;
    }

    public void setInvoicePayeeEmail(String invoicePayeeEmail) {
	this.invoicePayeeEmail = invoicePayeeEmail;
    }

    public PhoneNumber getInvoicePayeePhone() {
	return invoicePayeePhone;
    }

    public void setInvoicePayeePhone(PhoneNumber invoicePayeePhone) {
	this.invoicePayeePhone = invoicePayeePhone;
    }

    public TaxpayerNumber getInvoicePayeeTaxpayerNumber() {
	return invoicePayeeTaxpayerNumber;
    }

    public void setInvoicePayeeTaxpayerNumber(TaxpayerNumber invoicePayeeTaxpayerNumber) {
	this.invoicePayeeTaxpayerNumber = invoicePayeeTaxpayerNumber;
    }

    public LocalizationLanguage getInvoiceLanguage() {
	return invoiceLanguage;
    }

    public void setInvoiceLanguage(LocalizationLanguage invoiceLanguage) {
	this.invoiceLanguage = invoiceLanguage;
    }

    public String getAgreementNumber() {
	return agreementNumber;
    }

    public void setAgreementNumber(final String agreementNumber) {
	this.agreementNumber = agreementNumber;
    }
}
