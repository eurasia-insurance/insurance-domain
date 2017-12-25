package com.lapsa.insurance.domain;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class InsuranceProduct extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    protected CalculationData calculation = new CalculationData();

    @Embedded
    protected InsurancePeriodData period;

    // GENERATED

    public CalculationData getCalculation() {
	return calculation;
    }

    public void setCalculation(final CalculationData calculation) {
	this.calculation = calculation;
    }

    public InsurancePeriodData getPeriod() {
	return period;
    }

    public void setPeriod(final InsurancePeriodData period) {
	this.period = period;
    }
}
