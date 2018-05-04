package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.localization.Localizeds;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(71)
public class PersonalData extends Domain {

    private static final long serialVersionUID = 1L;

    // name

    @Basic
    @Column(name = "PERSON_NAME")
    private String name;

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    // surename

    @Basic
    @Column(name = "PERSON_SURENAME")
    private String surename;

    public String getSurename() {
	return surename;
    }

    public void setSurename(final String surename) {
	this.surename = surename;
    }

    // patronymic

    @Basic
    @Column(name = "PERSON_PATRONYMIC")
    private String patronymic;

    public String getPatronymic() {
	return patronymic;
    }

    public void setPatronymic(final String patronymic) {
	this.patronymic = patronymic;
    }

    // dateOfBirth

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "PERSON_DOB")
    private LocalDate dateOfBirth;

    public LocalDate getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    @Deprecated
    public LocalDate getDayOfBirth() {
	return getDateOfBirth();
    }

    @Deprecated
    public void setDayOfBirth(LocalDate dayOfBirth) {
	setDateOfBirth(dayOfBirth);
    }

    // sex

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "PERSON_GENDER")
    private Sex gender;

    public Sex getGender() {
	return gender;
    }

    public void setGender(final Sex gender) {
	this.gender = gender;
    }

    @Deprecated
    public Sex getSex() {
	return getGender();
    }

    @Deprecated
    public void setSex(final Sex sex) {
	setGender(sex);
    }

    // controls

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(getFullName()) //
		.orElseGet(() -> PERSONAL_DATA.localized(variant, locale)));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(dateOfBirth) //
		.map(Localizeds.localDateMapper(locale))//
		.map(PERSONAL_DATA_DOB.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(gender) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(PERSONAL_DATA_SEX.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }

    public String getFullName() {
	final StringJoiner sj = new StringJoiner(" ");

	MyOptionals.of(surename) //
		.map(String::trim)
		.map(MyStrings::capitalizeFirstLetter)
		.ifPresent(sj::add);

	MyOptionals.of(name) //
		.map(String::trim)
		.map(MyStrings::capitalizeFirstLetter)
		.ifPresent(sj::add);

	MyOptionals.of(name) //
		.map(x -> patronymic) // patronymic used only in conjuction with
				      // first name
		.map(MyStrings::nullOnEmpty)
		.map(String::trim)
		.map(MyStrings::capitalizeFirstLetter)
		.ifPresent(sj::add);

	return MyStrings.nullOnEmpty(sj.toString());
    }
}
