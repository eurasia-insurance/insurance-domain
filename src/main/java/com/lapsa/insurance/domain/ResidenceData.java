package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import com.lapsa.kz.country.KZCity;
import com.lapsa.kz.country.validators.ValidKZCity;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.javax.validation.NotEmptyString;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(103)
public class ResidenceData extends Domain {

    private static final long serialVersionUID = 1L;

    private Boolean resident;

    @NotNullValue
    @ValidKZCity
    private KZCity city;

    @NotNullValue
    @NotEmptyString
    private String address;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(RESIDENCE_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(city) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(RESIDENCE_DATA_CITY.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(address)
		.map(RESIDENCE_DATA_ADDRESS.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(resident)
		.map(x -> x.booleanValue() ? RESIDENCE_DATA_RESIDENT : RESIDENCE_DATA_NONRESIDENT)
		.map(Localized.toLocalizedMapper(variant, locale))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public Boolean isResident() {
	return resident;
    }

    public void setResident(final Boolean resident) {
	this.resident = resident;
    }

    public KZCity getCity() {
	return city;
    }

    public void setCity(final KZCity city) {
	this.city = city;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(final String address) {
	this.address = address;
    }
}
