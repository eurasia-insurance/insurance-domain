package com.lapsa.insurance.domain.policy;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Stream;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.lapsa.insurance.domain.EntitySuperclass;
import com.lapsa.insurance.domain.InsuranceProduct;
import com.lapsa.insurance.domain.InsurantData;
import com.lapsa.insurance.elements.CancelationReason;

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

    // insuredDrivers

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "POLICY_ID")
    private List<PolicyDriver> insuredDrivers = new ArrayList<>();

    public List<PolicyDriver> getInsuredDrivers() {
	return insuredDrivers;
    }

    protected void setInsuredDrivers(final List<PolicyDriver> insuredDrivers) {
	this.insuredDrivers = insuredDrivers;
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

    // insuredVehicles

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "POLICY_ID")
    private List<PolicyVehicle> insuredVehicles = new ArrayList<>();

    public List<PolicyVehicle> getInsuredVehicles() {
	return insuredVehicles;
    }

    protected void setInsuredVehicles(final List<PolicyVehicle> insuredVehicles) {
	this.insuredVehicles = insuredVehicles;
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

    // number

    @Basic
    @Column(name = "POLICY_NUMBER")
    private String number;

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    // policyDate

    @Transient
    private LocalDate policyDate;

    public LocalDate getPolicyDate() {
	return policyDate;
    }

    public void setPolicyDate(LocalDate policyDate) {
	this.policyDate = policyDate;
    }

    // dateOfIssue

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_ISSUE")
    private LocalDate dateOfIssue;

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

    @Embedded
    private InsurantData insurant;

    public InsurantData getInsurant() {
	return insurant;
    }

    public void setInsurant(InsurantData insurant) {
	this.insurant = insurant;
    }

    // dateOfTermination

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_TERMINATION")
    private LocalDate dateOfTermination;

    public LocalDate getDateOfTermination() {
	return dateOfTermination;
    }

    public void setDateOfTermination(LocalDate dateOfTermination) {
	this.dateOfTermination = dateOfTermination;
    }

    // terminationReason

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "TERMINATION_REASON")
    private CancelationReason terminationReason;

    public CancelationReason getTerminationReason() {
	return terminationReason;
    }

    public void setTerminationReason(CancelationReason terminationReason) {
	this.terminationReason = terminationReason;
    }

    // controls

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.streamOf(getInsuredDrivers()) //
		.orElseGet(Stream::empty) //
		.filter(MyObjects::nonNull) //
		.forEach(EntitySuperclass::unlazy);
	MyOptionals.streamOf(getInsuredVehicles()) //
		.orElseGet(Stream::empty) //
		.filter(MyObjects::nonNull) //
		.forEach(EntitySuperclass::unlazy);
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
}