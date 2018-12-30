package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.time.Instant;
import java.util.Currency;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lapsa.insurance.elements.PaymentStatus;
import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.localization.Localizeds;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(127)
public class PaymentData extends Domain {

    private static final long serialVersionUID = 1L;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus status = PaymentStatus.UNDEFINED;

    @Basic
    @Column(name = "PAYMENT_INVOICE_NUMBER")
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

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(methodName) //
		.orElseGet(() -> PAYMENT_DATA.localized(variant, locale)));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(status) //
		.filter(PaymentStatus::isDefined) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(FIELD_STATUS.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(invoiceNumber) //
		.map(PAYMENT_INVOICE_NUMBER.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(instant) //
		.map(Localizeds.instantMapper(locale)) //
		.map(PAYMENT_POST_INSTANT.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(reference) //
		.map(PAYMENT_POST_REFERENCE.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(payerName) //
		.map(PAYMENT_PAYER_NAME.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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
}
