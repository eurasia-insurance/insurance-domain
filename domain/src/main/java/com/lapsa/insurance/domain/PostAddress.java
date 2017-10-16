package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.kz.country.KZCity;
import com.lapsa.kz.country.validators.ValidKZCity;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.javax.validation.NotEmptyString;
import tech.lapsa.javax.validation.NotNullValue;

public class PostAddress extends BaseDomain {
    private static final long serialVersionUID = -5557010401647487544L;
    private static final int PRIME = 97;
    private static final int MULTIPLIER = PRIME;

    @Override
    protected int getPrime() {
	return PRIME;
    }

    @Override
    protected int getMultiplier() {
	return MULTIPLIER;
    }

    @NotNullValue
    @NotEmptyString
    private String postIndex;

    @NotNullValue
    @ValidKZCity(invalidValues = KZCity.OTHER)
    private KZCity city;

    @NotNullValue
    @NotEmptyString
    private String street;

    private Map<LocalizationLanguage, String> streetLocalization = new HashMap<>();

    @Override
    public String localized(LocalizationVariant variant, Locale locale) {
	StringBuilder sb = new StringBuilder();

	sb.append(POST_ADDRESS.localized(variant, locale));

	StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(postIndex)
		.ifPresent(sj::add);

	MyOptionals.of(city) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(streetLocalization.getOrDefault(LocalizationLanguage.byLocale(locale), street))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public String getPostIndex() {
	return postIndex;
    }

    public void setPostIndex(String postIndex) {
	this.postIndex = postIndex;
    }

    public KZCity getCity() {
	return city;
    }

    public void setCity(KZCity city) {
	this.city = city;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public Map<LocalizationLanguage, String> getStreetLocalization() {
	return streetLocalization;
    }

    protected void setStreetLocalization(Map<LocalizationLanguage, String> streetLocalization) {
	this.streetLocalization = streetLocalization;
    }
}
