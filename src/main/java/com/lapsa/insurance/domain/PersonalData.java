package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.StringJoiner;

import com.lapsa.insurance.elements.Sex;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.localization.Localizeds;
import tech.lapsa.javax.validation.NotEmptyString;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.javax.validation.ValidDateOfBirth;
import tech.lapsa.javax.validation.ValidHumanName;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(71)
public class PersonalData extends Domain {

    private static final long serialVersionUID = 1L;

    @NotNullValue
    @NotEmptyString
    @ValidHumanName
    private String name;

    @NotNullValue
    @NotEmptyString
    @ValidHumanName
    private String surename;

    @NotNullValue
    @NotEmptyString
    @ValidHumanName
    private String patronymic;

    @NotNullValue
    @ValidDateOfBirth
    private LocalDate dayOfBirth;

    @NotNullValue
    private Sex sex;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(getFullName()) //
		.orElseGet(() -> PERSONAL_DATA.localized(variant, locale)));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(dayOfBirth) //
		.map(Localizeds.localDateMapper(locale))//
		.map(PERSONAL_DATA_DOB.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(sex) //
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

    // GENERATED

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public String getSurename() {
	return surename;
    }

    public void setSurename(final String surename) {
	this.surename = surename;
    }

    public String getPatronymic() {
	return patronymic;
    }

    public void setPatronymic(final String patronymic) {
	this.patronymic = patronymic;
    }

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    public void setDayOfBirth(final LocalDate dayOfBirth) {
	this.dayOfBirth = dayOfBirth;
    }

    public Sex getSex() {
	return sex;
    }

    public void setSex(final Sex sex) {
	this.sex = sex;
    }
}
