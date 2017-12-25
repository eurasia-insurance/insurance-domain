package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import com.lapsa.international.country.Country;
import com.lapsa.international.country.validators.ValidCountry;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(61)
public class OriginData extends Domain {

    private static final long serialVersionUID = 1L;

    @NotNullValue
    @ValidCountry
    private Country country;

    // GENERATED

    public Country getCountry() {
	return country;
    }

    public void setCountry(final Country country) {
	this.country = country;
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(ORIGIN_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(country) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(ORIGIN_DATA_COUNTRY.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }
}
