package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(193)
public class CompanyData extends Domain {

    private static final long serialVersionUID = 1L;

    // name

    @Basic
    @Column(name = "COMPANY_NAME")
    private String name;

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    // controls

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(getFullName()) //
		.orElseGet(() -> COMPANY_DATA.localized(variant, locale)));

	return sb
		.toString();
    }

    public String getFullName() {
	return name;
    }
}
