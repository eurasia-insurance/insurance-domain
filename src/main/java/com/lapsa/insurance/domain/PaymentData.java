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

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.localization.Localizeds;
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
    @Column(name = "PAYMENT_CARD")
    private String card;

    @Basic
    @Column(name = "PAYMENT_AMOUNT")
    private Double amount;

    @Basic
    @Column(name = "PAYMENT_CURRENCY")
    private Currency currency;

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

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public String getInvoiceNumber() {
	return invoiceNumber;
    }

    public void setInvoiceNumber(final String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
    }

    public PaymentStatus getStatus() {
	return status;
    }

    public void setStatus(final PaymentStatus status) {
	this.status = status;
    }

    public String getReference() {
	return reference;
    }

    public void setReference(final String reference) {
	this.reference = reference;
    }

    public void setInstant(final Instant instant) {
	this.instant = instant;
    }

    public Instant getInstant() {
	return instant;
    }

    public String getMethodName() {
	return methodName;
    }

    public void setMethodName(final String methodName) {
	this.methodName = methodName;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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
}
