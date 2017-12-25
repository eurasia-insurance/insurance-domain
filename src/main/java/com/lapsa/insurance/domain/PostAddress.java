package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.UniqueConstraint;

import com.lapsa.international.localization.LocalizationLanguage;
import com.lapsa.kz.country.KZCity;
import com.lapsa.kz.country.validators.ValidKZCity;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.javax.validation.NotEmptyString;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(97)
public class PostAddress extends Domain {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "ADDRESS_POSTINDEX")
    @NotNullValue
    @NotEmptyString
    private String postIndex;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "ADDRESS_CITY")
    @NotNullValue
    @ValidKZCity(invalidValues = KZCity.OTHER)
    private KZCity city;

    @Basic
    @Column(name = "ADDRESS_STREET")
    @NotNullValue
    @NotEmptyString
    private String street;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "LANGUAGE")
    @Column(name = "VALUE")
    @CollectionTable(name = "ADDRESS_STREET_LOCALIZATION", //
	    joinColumns = {
		    @JoinColumn(name = "ENTITY_ID") //
	    }, //
	    uniqueConstraints = {
		    @UniqueConstraint(columnNames = { "ENTITY_ID", "LANGUAGE" }) //
	    })
    private Map<LocalizationLanguage, String> streetLocalization = new HashMap<>();

    @Override
    public void unlazy() {
	super.unlazy();
	getStreetLocalization().size();
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringJoiner sj = new StringJoiner(", ");
	sj.setEmptyValue(POST_ADDRESS.localized(variant, locale));

	MyOptionals.of(postIndex)
		.ifPresent(sj::add);

	MyOptionals.of(city) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(streetLocalization.getOrDefault(LocalizationLanguage.byLocale(locale), street))
		.ifPresent(sj::add);

	return sj.toString();
    }

    // GENERATED

    public String getPostIndex() {
	return postIndex;
    }

    public void setPostIndex(final String postIndex) {
	this.postIndex = postIndex;
    }

    public KZCity getCity() {
	return city;
    }

    public void setCity(final KZCity city) {
	this.city = city;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(final String street) {
	this.street = street;
    }

    public Map<LocalizationLanguage, String> getStreetLocalization() {
	return streetLocalization;
    }

    protected void setStreetLocalization(final Map<LocalizationLanguage, String> streetLocalization) {
	this.streetLocalization = streetLocalization;
    }
}
