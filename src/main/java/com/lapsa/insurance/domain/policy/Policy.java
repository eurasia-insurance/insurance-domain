package com.lapsa.insurance.domain.policy;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lapsa.insurance.domain.BaseEntity;
import com.lapsa.insurance.domain.InsuranceProduct;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.util.MyCurrencies;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POLICY")
@HashCodePrime(79)
public class Policy extends InsuranceProduct {

    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "POLICY_ID")
    private List<PolicyDriver> insuredDrivers = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "POLICY_ID")
    private List<PolicyVehicle> insuredVehicles = new ArrayList<>();

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.streamOf(getInsuredDrivers()) //
		.orElseGet(Stream::empty) //
		.filter(MyObjects::nonNull) //
		.forEach(BaseEntity::unlazy);
	MyOptionals.streamOf(getInsuredVehicles()) //
		.orElseGet(Stream::empty) //
		.filter(MyObjects::nonNull) //
		.forEach(BaseEntity::unlazy);
    }

    public PolicyDriver addDriver(final PolicyDriver driver) {
	MyObjects.requireNonNull(driver, "Value must not be null");
	if (insuredDrivers == null)
	    insuredDrivers = new ArrayList<>();
	insuredDrivers.add(driver);
	return driver;
    }

    public PolicyDriver removeDriver(final PolicyDriver driver) {
	MyObjects.requireNonNull(driver, "Value must not be null");
	if (insuredDrivers != null)
	    insuredDrivers.remove(driver);
	return driver;
    }

    public PolicyVehicle addVehicle(final PolicyVehicle vehicle) {
	MyObjects.requireNonNull(vehicle, "Value must not be null");
	if (insuredVehicles == null)
	    insuredVehicles = new ArrayList<>();
	insuredVehicles.add(vehicle);
	return vehicle;
    }

    public PolicyVehicle removeVehicle(final PolicyVehicle vehicle) {
	MyObjects.requireNonNull(vehicle, "Value must not be null");
	if (insuredVehicles != null)
	    insuredVehicles.remove(vehicle);
	return vehicle;
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(POLICY.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(calculation) //
		.filter(x -> MyNumbers.nonZero(x.getAmount())) //
		.filter(x -> MyObjects.nonNull(x.getCurrency()))
		.map(x -> MyCurrencies.formatAmount(x.getAmount(), x.getCurrency(), locale)) //
		.map(POLICY_COST.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.streamOf(insuredDrivers) //
		.map(Stream::count)
		.map(String::valueOf)
		.map(POLICY_DRIVERS_COUNT.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.streamOf(insuredVehicles) //
		.map(Stream::count)
		.map(String::valueOf)
		.map(POLICY_VEHICLES_COUNT.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public List<PolicyDriver> getInsuredDrivers() {
	return insuredDrivers;
    }

    protected void setInsuredDrivers(final List<PolicyDriver> insuredDrivers) {
	this.insuredDrivers = insuredDrivers;
    }

    public List<PolicyVehicle> getInsuredVehicles() {
	return insuredVehicles;
    }

    protected void setInsuredVehicles(final List<PolicyVehicle> insuredVehicles) {
	this.insuredVehicles = insuredVehicles;
    }
}