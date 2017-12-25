package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localizeds;
import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "VEHICLE_CERTIFICATE")
@HashCodePrime(109)
public class VehicleCertificateData extends SidedScannedDocument {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "VEHICLE_REGISTRATION_NUMBER")
    private VehicleRegNumber registrationNumber;

    @Basic
    @Column(name = "VEHICLE_CERTITFICATE_NUMBER")
    private String number;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_ISSUE")
    private LocalDate dateOfIssue;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(VEHICLE_CERTIFICATE_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(number)
		.map(FIELD_NUMBER.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(dateOfIssue) //
		.map(Localizeds.localDateMapper(locale))//
		.map(VEHICLE_CERTIFICATE_DATA_ISSUED.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(registrationNumber)
		.map(VehicleRegNumber::getNumber) //
		.map(VEHICLE_CERTIFICATE_DATA_REGNUMBER.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public String getNumber() {
	return number;
    }

    public void setNumber(final String number) {
	this.number = number;
    }

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    public void setDateOfIssue(final LocalDate dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

    public VehicleRegNumber getRegistrationNumber() {
	return registrationNumber;
    }

    public void setRegistrationNumber(final VehicleRegNumber registrationNumber) {
	this.registrationNumber = registrationNumber;
    }
}
