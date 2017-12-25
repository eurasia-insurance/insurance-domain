package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Currency;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.util.MyCurrencies;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(3)
public class CalculationData extends Domain {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "CALCULATED_AMOUNT")
    private Double amount;

    // TODO REFACT : To be removed in the next release
    @Deprecated
    @Basic
    @Column(name = "PAID_AMOUNT_TO_BE_REMOVED_IN_THE_NEXT_RELEASE")
    private Double paidAmount;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "CALCULATED_CURRENCY")
    private Currency currency;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(CALCULATION_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	sj.add(MyOptionals.of(this) //
		.filter(x -> MyNumbers.positive(amount))
		.filter(x -> MyObjects.nonNull(currency))
		.map(x -> MyCurrencies.formatAmount(amount, currency, locale)) //
		.orElseGet(() -> "<" + CALCULATION_DATA_UNDEFINED.localized(variant, locale) + ">"));

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENEERATED

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

    @Deprecated
    public Double getPaidAmount() {
	return paidAmount;
    }

    @Deprecated
    public void setPaidAmount(Double paidAmount) {
	this.paidAmount = paidAmount;
    }
}
