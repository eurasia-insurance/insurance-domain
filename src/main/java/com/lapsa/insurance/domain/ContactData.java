package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import com.lapsa.international.phone.PhoneNumber;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(13)
public class ContactData extends Domain {

    private static final long serialVersionUID = 1L;

    private PhoneNumber phone;

    private String email;

    private String siteUrl;

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(CONTACT_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(email) //
		.ifPresent(sj::add);

	MyOptionals.of(phone) //
		.map(PhoneNumber::getFormatted) //
		.ifPresent(sj::add);

	MyOptionals.of(siteUrl) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public PhoneNumber getPhone() {
	return phone;
    }

    public void setPhone(final PhoneNumber phone) {
	this.phone = phone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public String getSiteUrl() {
	return siteUrl;
    }

    public void setSiteUrl(final String siteUrl) {
	this.siteUrl = siteUrl;
    }

}
