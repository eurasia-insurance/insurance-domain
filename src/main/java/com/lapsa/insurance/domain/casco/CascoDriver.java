package com.lapsa.insurance.domain.casco;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lapsa.insurance.domain.Driver;
import com.lapsa.insurance.domain.PersonalData;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "CASCO_DRIVER")
@HashCodePrime(113)
public class CascoDriver extends Driver {

    private static final long serialVersionUID = 1L;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(CASCO_DRIVER.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(personalData) //
		.map(PersonalData::getFullName) //
		.ifPresent(sj::add);

	MyOptionals.of(idNumber) //
		.map(TaxpayerNumber::getNumber) //
		.map(FIELD_ID_NUMBER.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

}
