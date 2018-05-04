package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(191)
public class InsurantData extends Domain {

    private static final long serialVersionUID = 1L;

    // personal

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "name", column = @Column(name = "INSURANT_PERSON_NAME")),
	    @AttributeOverride(name = "surename", column = @Column(name = "INSURANT_PERSON_SURENAME")),
	    @AttributeOverride(name = "patronymic", column = @Column(name = "INSURANT_PERSON_PATRONYMIC")),
	    @AttributeOverride(name = "dateOfBirth", column = @Column(name = "INSURANT_PERSON_DOB")),
	    @AttributeOverride(name = "gender", column = @Column(name = "INSURANT_PERSON_GENDER"))
    })
    private PersonalData personal;

    public PersonalData getPersonal() {
	return personal;
    }

    public void setPersonal(final PersonalData personal) {
	this.personal = personal;
    }

    // company

    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "name", column = @Column(name = "INSURANT_COMPANY_NAME")),
    })
    private CompanyData company;

    public CompanyData getCompany() {
	return company;
    }

    public void setCompany(CompanyData company) {
	this.company = company;
    }

    // email

    @Basic
    @Column(name = "INSURANT_EMAIL")
    private String email;

    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    // idNumber

    @Basic
    @Column(name = "INSURANT_ID_NUMBER")
    private TaxpayerNumber idNumber;

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    public void setIdNumber(final TaxpayerNumber idNumber) {
	this.idNumber = idNumber;
    }

    // phone

    @Basic
    @Column(name = "INSURANT_PHONE_NUMBER")
    private PhoneNumber phone;

    public PhoneNumber getPhone() {
	return phone;
    }

    public void setPhone(final PhoneNumber phone) {
	this.phone = phone;
    }

    // controls

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(REQUESTER_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(personal) //
		.map(PersonalData::getFullName)
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
}
