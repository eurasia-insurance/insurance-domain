package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;
import static com.lapsa.international.phone.CountryCode.*;

import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.AssertTrue;

import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.international.phone.PhoneNumber;
import com.lapsa.international.phone.validators.ValidPhoneNumber;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.javax.validation.ValidEmail;
import tech.lapsa.javax.validation.ValidHumanName;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.kz.taxpayer.validators.ValidTaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(73)
public class RequesterData extends Domain {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "REQUESTER_NAME")
    @ValidHumanName
    private String name;

    @Basic
    @Column(name = "REQUESTER_EMAIL")
    @ValidEmail
    private String email;

    @Basic
    @Column(name = "REQUESTER_ID_NUMBER")
    @ValidTaxpayerNumber
    private TaxpayerNumber idNumber;

    @Basic
    @Column(name = "REQUESTER_PHONE_NUMBER")
    @NotNullValue
    @ValidPhoneNumber(countriesRequired = { KZ, RU, GE, BY, BY, CN, KG, UZ, UA })
    private PhoneNumber phone;

    @Basic
    @Column(name = "REQUESTER_ALLOW_SPAM")
    private boolean allowSpam;

    @Basic
    @Column(name = "REQUESTER_ALLOW_PROCESS_PERSONAL_DATA")
    @AssertTrue(message = "{com.lapsa.insurance.domain.RequesterData.allowProcessPersonalData.AssertTrue.message}")
    private boolean allowProcessPersonalData;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "REQUESTER_PREFER_LANGUAGE")
    private LocalizationLanguage preferLanguage;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(REQUESTER_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(name) //
		.ifPresent(sj::add);

	MyOptionals.of(idNumber) //
		.map(TaxpayerNumber::getNumber) //
		.map(FIELD_ID_NUMBER.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(email) //
		.map(FIELD_EMAIL.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(phone) //
		.map(PhoneNumber::getFormatted) //
		.map(FIELD_PHONE.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public PhoneNumber getPhone() {
	return phone;
    }

    public void setPhone(final PhoneNumber phone) {
	this.phone = phone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    public void setIdNumber(final TaxpayerNumber idNumber) {
	this.idNumber = idNumber;
    }

    public LocalizationLanguage getPreferLanguage() {
	return preferLanguage;
    }

    public void setPreferLanguage(final LocalizationLanguage preferLanguage) {
	this.preferLanguage = preferLanguage;
    }

    public boolean isAllowSpam() {
	return allowSpam;
    }

    public void setAllowSpam(final boolean allowSpam) {
	this.allowSpam = allowSpam;
    }

    public boolean isAllowProcessPersonalData() {
	return allowProcessPersonalData;
    }

    public void setAllowProcessPersonalData(final boolean allowProcessPersonalData) {
	this.allowProcessPersonalData = allowProcessPersonalData;
    }
}
