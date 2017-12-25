package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import tech.lapsa.patterns.domain.HashCodePrime;

@Embeddable
@HashCodePrime(19)
public class GeoPoint extends Domain {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "GEO_LATITUDE")
    private double latitude;

    @Basic
    @Column(name = "GEO_LONGITUDE")
    private double longitude;

    public GeoPoint() {
    }

    public GeoPoint(final double latitude, final double longitude) {
	this.latitude = latitude;
	this.longitude = longitude;
    }

    private static final NumberFormat GEO_LONG_NUMBER_FORMAT = NumberFormat.getNumberInstance();
    {
	GEO_LONG_NUMBER_FORMAT.setMinimumFractionDigits(6);
	GEO_LONG_NUMBER_FORMAT.setMaximumFractionDigits(6);
	GEO_LONG_NUMBER_FORMAT.setGroupingUsed(false);
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(GEO_POINT.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	sj.add(GEO_POINT_LAT.localized(variant, locale) + ": " + GEO_LONG_NUMBER_FORMAT.format(latitude) + "°");
	sj.add(GEO_POINT_LNG.localized(variant, locale) + ": " + GEO_LONG_NUMBER_FORMAT.format(longitude) + "°");

	return sb.append(sj.toString()) //
		.toString();
    }

    // GENERATED

    public double getLatitude() {
	return latitude;
    }

    public void setLatitude(final double latitude) {
	this.latitude = latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(final double longitude) {
	this.longitude = longitude;
    }
}