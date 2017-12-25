package com.lapsa.insurance.domain.policy;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lapsa.insurance.domain.Driver;
import com.lapsa.insurance.domain.PersonalData;
import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;
import com.lapsa.insurance.validation.ValidInsuranceAgeClass;
import com.lapsa.insurance.validation.ValidInsuranceClassType;
import com.lapsa.insurance.validation.ValidInsuranceExpirienceClass;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POLICY_DRIVER")
@HashCodePrime(47)
public class PolicyDriver extends Driver {

    private static final long serialVersionUID = 1L;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "INSURANCE_CLASS")
    @NotNullValue
    @ValidInsuranceClassType
    private InsuranceClassType insuranceClassType;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "AGE_CLASS")
    @NotNullValue
    @ValidInsuranceAgeClass
    private InsuredAgeClass ageClass;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "EXPIRIENCE_CLASS")
    @NotNullValue
    @ValidInsuranceExpirienceClass
    private InsuredExpirienceClass expirienceClass;

    @Transient
    private boolean fetched = false;

    @Basic
    @Column(name = "IS_HAS_ANY_PRIVILEGE")
    private boolean hasAnyPrivilege = false;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(POLICY_DRIVER.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(personalData) //
		.map(PersonalData::getFullName) //
		.ifPresent(sj::add);

	MyOptionals.of(idNumber) //
		.map(TaxpayerNumber::getNumber) //
		.map(FIELD_ID_NUMBER.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(insuranceClassType) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(ageClass) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(expirienceClass) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public InsuranceClassType getInsuranceClassType() {
	return insuranceClassType;
    }

    public void setInsuranceClassType(final InsuranceClassType insuranceClassType) {
	this.insuranceClassType = insuranceClassType;
    }

    public InsuredAgeClass getAgeClass() {
	return ageClass;
    }

    public void setAgeClass(final InsuredAgeClass ageClass) {
	this.ageClass = ageClass;
    }

    public InsuredExpirienceClass getExpirienceClass() {
	return expirienceClass;
    }

    public void setExpirienceClass(final InsuredExpirienceClass expirienceClass) {
	this.expirienceClass = expirienceClass;
    }

    public boolean isFetched() {
	return fetched;
    }

    public void setFetched(final boolean fetched) {
	this.fetched = fetched;
    }

    public boolean isHasAnyPrivilege() {
	return hasAnyPrivilege;
    }

    public void setHasAnyPrivilege(final boolean hasAnyPrivilege) {
	this.hasAnyPrivilege = hasAnyPrivilege;
    }
}
