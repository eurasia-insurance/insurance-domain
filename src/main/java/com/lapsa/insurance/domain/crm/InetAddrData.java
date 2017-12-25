package com.lapsa.insurance.domain.crm;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.lapsa.insurance.domain.Domain;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(181)
public class InetAddrData extends Domain {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "INET_ADDRESS")
    private String inetAddress;

    @Basic
    @Column(name = "INET_HOST")
    private String inetHost;

    public InetAddrData() {
    }

    public InetAddrData(final String inetAddress, final String inetHost) {
	this.inetAddress = inetAddress;
	this.inetHost = inetHost;
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(INET_ADDR_DATA.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(inetAddress) //
		.ifPresent(sj::add);

	MyOptionals.of(inetHost) //
		.map(INET_ADDR_DATA_HOST.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public String getInetAddress() {
	return inetAddress;
    }

    public void setInetAddress(final String inetAddress) {
	this.inetAddress = inetAddress;
    }

    public String getInetHost() {
	return inetHost;
    }

    public void setInetHost(final String inetHost) {
	this.inetHost = inetHost;
    }
}
