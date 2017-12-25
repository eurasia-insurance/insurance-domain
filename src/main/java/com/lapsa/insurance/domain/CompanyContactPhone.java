package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.international.phone.PhoneNumber;
import com.lapsa.international.phone.PhoneType;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POS_CONTACT_PHONE")
@HashCodePrime(7)
public class CompanyContactPhone extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "PHONE_NUMBER")
    private PhoneNumber phone;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "PHONE_TYPE")
    private PhoneType phoneType;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(phoneType) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(MyStrings::capitalizeFirstLetter) //
		.orElseGet(() -> COMPANY_CONTACT_PHONE.localized(variant, locale)));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	sj.add(MyOptionals.of(phone) //
		.map(PhoneNumber::getFormatted) //
		.orElseGet(() -> "<" + COMPANY_CONTACT_PHONE_UNDEFINED.localized(variant, locale) + ">"));

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public PhoneNumber getPhone() {
	return phone;
    }

    public void setPhone(final PhoneNumber phone) {
	this.phone = phone;
    }

    public PhoneType getPhoneType() {
	return phoneType;
    }

    public void setPhoneType(final PhoneType phoneType) {
	this.phoneType = phoneType;
    }
}
