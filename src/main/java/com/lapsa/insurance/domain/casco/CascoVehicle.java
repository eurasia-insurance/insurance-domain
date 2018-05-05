package com.lapsa.insurance.domain.casco;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Currency;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.domain.Vehicle;
import com.lapsa.insurance.domain.VehicleCertificateData;
import com.lapsa.insurance.elements.CascoCarAgeClass;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.util.MyCurrencies;
import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "CASCO_VEHICLE")
@HashCodePrime(157)
public class CascoVehicle extends Vehicle {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "VEHICLE_COST")
    private Double cost;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "VEHICLE_CAR_AGE_CLASS")
    private CascoCarAgeClass carAgeClass;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(getFullName()) //
		.orElseGet(() -> CASCO_VEHICLE.localized(variant, locale)));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(cost)
		.map(x -> MyCurrencies.formatAmount(x, Currency.getInstance("KZT"), locale))
		.map(CASCO_VEHICLE_COST.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(carAgeClass) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(CASCO_VEHICLE_AGE_CLASS.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(certificateData) //
		.map(VehicleCertificateData::getRegistrationNumber) //
		.map(VehicleRegNumber::getNumber) //
		.map(POLICY_VEHICLE_REG_NUMBER.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public Double getCost() {
	return cost;
    }

    public void setCost(final Double cost) {
	this.cost = cost;
    }

    public CascoCarAgeClass getCarAgeClass() {
	return carAgeClass;
    }

    public void setCarAgeClass(final CascoCarAgeClass carAgeClass) {
	this.carAgeClass = carAgeClass;
    }
}
