package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lapsa.insurance.elements.IdentityCardType;
import com.lapsa.insurance.validation.ValidIdentityCardType;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.localization.Localizeds;
import tech.lapsa.javax.validation.NotEmptyString;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.javax.validation.ValidDateOfIssue;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "IDENTITY_CARD")
@HashCodePrime(37)
public class IdentityCardData extends SidedScannedDocument {

    private static final long serialVersionUID = 1L;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_ISSUE")
    @NotNullValue
    @ValidDateOfIssue
    private LocalDate dateOfIssue;

    @Basic
    @Column(name = "ISSUING_AUTHORITY")
    @NotNullValue
    @NotEmptyString
    private String issuingAuthority;

    @Basic
    @Column(name = "NUMBER")
    @NotNullValue
    @NotEmptyString
    private String number;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    @NotNullValue
    @ValidIdentityCardType
    private IdentityCardType type;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(type) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(MyStrings::capitalizeFirstLetter) //
		.orElseGet(() -> IDENTITY_CARD_DATA.localized(variant, locale)));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(number) //
		.map(FIELD_NUMBER.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(dateOfIssue) //
		.map(Localizeds.localDateMapper(locale)) //
		.map(IDENTITY_CARD_DATA_ISSUED.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public LocalDate getDateOfIssue() {
	return dateOfIssue;
    }

    public void setDateOfIssue(final LocalDate dateOfIssue) {
	this.dateOfIssue = dateOfIssue;
    }

    public String getIssuingAuthority() {
	return issuingAuthority;
    }

    public void setIssuingAuthority(final String issuingAuthority) {
	this.issuingAuthority = issuingAuthority;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(final String number) {
	this.number = number;
    }

    public IdentityCardType getType() {
	return type;
    }

    public void setType(final IdentityCardType type) {
	this.type = type;
    }
}
