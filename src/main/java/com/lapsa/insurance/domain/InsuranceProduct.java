package com.lapsa.insurance.domain;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class InsuranceProduct extends EntitySuperclass {

    private static final long serialVersionUID = 1L;

    // calculation

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "amount", column = @Column(name = "CALCULATED_AMOUNT")),
	    @AttributeOverride(name = "currency", column = @Column(name = "CALCULATED_CURRENCY")) })
    protected CalculationData calculation = new CalculationData();

    public CalculationData getCalculation() {
	return calculation;
    }

    public void setCalculation(final CalculationData calculation) {
	this.calculation = calculation;
    }

    // actual

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "amount", column = @Column(name = "ACTUAL_AMOUNT")),
	    @AttributeOverride(name = "currency", column = @Column(name = "ACTUAL_CURRENCY")) })
    protected CalculationData actual = new CalculationData();

    public CalculationData getActual() {
	return actual;
    }

    public void setActual(final CalculationData actual) {
	this.actual = actual;
    }


    // paymentDate

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_PAYMENT")
    private LocalDate paymentDate;

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
	this.paymentDate = paymentDate;
    }

    // period

    @Embedded
    protected InsurancePeriodData period;

    public InsurancePeriodData getPeriod() {
	return period;
    }

    public void setPeriod(final InsurancePeriodData period) {
	this.period = period;
    }
}
