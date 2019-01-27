package com.lapsa.insurance.domain;

import java.time.Instant;
import java.util.Arrays;
import java.util.Currency;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.elements.InsuranceProductType;
import com.lapsa.insurance.elements.InsuranceRequestCancellationReason;
import com.lapsa.insurance.elements.InsuranceRequestStatus;
import com.lapsa.insurance.elements.InsuranceRequestType;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.kz.taxpayer.TaxpayerNumber;

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
    private String paymentReference;

    @Basic
    @Column(name = "PAYMENT_INSTANT")
    private Instant paymentInstant;

    @Basic
    @Column(name = "PAYMENT_CANCELED_INSTANT")
    private Instant paymentCanceledInstant;

    @Basic
    @Column(name = "PAYMENT_METHOD_NAME")
    private String paymentMethodName;

    @Basic
    @Column(name = "PAYER_NAME")
    private String payerName;

    @Basic
    @Column(name = "PAYMENT_CARD")
    private String paymentCard;

    @Basic
    @Column(name = "PAYMENT_CARD_BANK")
    private String cardBank;

    @Basic
    @Column(name = "PAYMENT_AMOUNT")
    private Double paymentAmount;

    @Basic
    @Column(name = "PAYMENT_CURRENCY")
    private Currency paymentCurrency;

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

    public void setInsuranceRequestCancellationReason(
	    final InsuranceRequestCancellationReason insuranceRequestCancellationReason) {
	this.insuranceRequestCancellationReason = insuranceRequestCancellationReason;
    }

    public String getInvoiceNumber() {
	return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
    }

    @Deprecated
    public String getReference() {
	return getPaymentReference();
    }

    @Deprecated
    public void setReference(String reference) {
	setPaymentReference(reference);
    }

    public String getPaymentReference() {
	return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
	this.paymentReference = paymentReference;
    }

    @Deprecated
    public Instant getInstant() {
	return getPaymentInstant();
    }

    @Deprecated
    public void setInstant(Instant instant) {
	setPaymentInstant(instant);
    }

    public Instant getPaymentInstant() {
	return paymentInstant;
    }

    public void setPaymentInstant(Instant paymentInstant) {
	this.paymentInstant = paymentInstant;
    }
    
    public Instant getPaymentCanceledInstant() {
        return paymentCanceledInstant;
    }

    public void setPaymentCanceledInstant(Instant paymentCanceledInstant) {
        this.paymentCanceledInstant = paymentCanceledInstant;
    }

    @Deprecated
    public String getMethodName() {
	return getPaymentMethodName();
    }

    @Deprecated
    public void setMethodName(String methodName) {
	setPaymentMethodName(methodName);
    }

    public String getPaymentMethodName() {
	return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
	this.paymentMethodName = paymentMethodName;
    }

    public String getPayerName() {
	return payerName;
    }

    public void setPayerName(String payerName) {
	this.payerName = payerName;
    }

    @Deprecated
    public String getCard() {
	return getPaymentCard();
    }

    @Deprecated
    public void setCard(String card) {
	setPaymentCard(card);
    }

    public String getPaymentCard() {
	return paymentCard;
    }

    public void setPaymentCard(String paymentCard) {
	this.paymentCard = paymentCard;
    }

    public String getCardBank() {
	return cardBank;
    }

    public void setCardBank(String cardBank) {
	this.cardBank = cardBank;
    }

    @Deprecated
    public Double getAmount() {
	return getPaymentAmount();
    }

    @Deprecated
    public void setAmount(Double amount) {
	setPaymentAmount(amount);
    }

    public Double getPaymentAmount() {
	return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
	this.paymentAmount = paymentAmount;
    }

    @Deprecated
    public Currency getCurrency() {
	return getPaymentCurrency();
    }

    @Deprecated
    public void setCurrency(Currency currency) {
	setPaymentCurrency(currency);
    }

    public Currency getPaymentCurrency() {
	return paymentCurrency;
    }

    public void setPaymentCurrency(Currency paymentCurrency) {
	this.paymentCurrency = paymentCurrency;
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
