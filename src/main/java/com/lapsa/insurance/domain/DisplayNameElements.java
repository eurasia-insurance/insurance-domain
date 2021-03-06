package com.lapsa.insurance.domain;

import java.util.Locale;
import java.util.function.Function;

import tech.lapsa.java.commons.localization.LocalizedElement;

public enum DisplayNameElements implements LocalizedElement {
    CONTACT_DATA,
    //
    CALCULATION_DATA,
    CALCULATION_DATA_UNDEFINED,
    //
    INSURANCE_PERIOD_DATA,
    INSURANCE_PERIOD_DATA_FROM,
    INSURANCE_PERIOD_DATA_TILL,
    //
    DRIVER_LICENSE_DATA,
    DRIVER_LICENSE_DATA_ISSUED,
    //
    IDENTITY_CARD_DATA,
    IDENTITY_CARD_DATA_ISSUED,
    //
    VEHICLE_CERTIFICATE_DATA,
    VEHICLE_CERTIFICATE_DATA_ISSUED,
    VEHICLE_CERTIFICATE_DATA_REGNUMBER,
    //
    REQUESTER_DATA,
    //
    OBTAINING_DATA,
    //
    PAYMENT_PAYER_NAME,
    PAYMENT_DATA,
    PAYMENT_INVOICE_NUMBER,
    PAYMENT_POST_INSTANT,
    PAYMENT_POST_REFERENCE,
    //
    PERSONAL_DATA,
    PERSONAL_DATA_DOB,
    PERSONAL_DATA_SEX,
    //
    COMPANY_DATA,
    //
    POLICY_VEHICLE,
    POLICY_VEHICLE_CLASS,
    POLICY_VEHICLE_AGE_CLASS,
    POLICY_VEHICLE_REG_NUMBER,
    //
    POLICY_DRIVER,
    //
    CASCO_VEHICLE,
    CASCO_VEHICLE_COST,
    CASCO_VEHICLE_AGE_CLASS,
    //
    CASCO_DRIVER,
    //
    USER,
    USER_LOGIN,
    //
    USER_LOGIN_LOGIN,
    //
    USER_GROUP,
    USER_GRPOUP_MEMBER,
    //
    INET_ADDR_DATA,
    INET_ADDR_DATA_HOST,
    //
    UTM_DATA,
    UTM_DATA_SOURCE,
    UTM_DATA_MEDIUM,
    UTM_DATA_CAMPAIGN,
    UTM_DATA_CONTENT,
    UTM_DATA_TERM,
    //
    POLICY,
    POLICY_COST,
    POLICY_DRIVERS_COUNT,
    POLICY_VEHICLES_COUNT,
    //
    CASCO,
    CASCO_COST,
    CASCO_CASCO_VEHICLE_COST,
    //
    RESIDENCE_DATA,
    RESIDENCE_DATA_RESIDENT,
    RESIDENCE_DATA_NONRESIDENT,
    RESIDENCE_DATA_CITY,
    RESIDENCE_DATA_ADDRESS,
    //
    ORIGIN_DATA,
    ORIGIN_DATA_COUNTRY,
    //
    POLICY_REQUEST,
    //
    CASCO_REQUEST,
    //
    CALLBACK_REQUEST,
    //
    FIELD_ID_NUMBER,
    FIELD_EMAIL,
    FIELD_PHONE,
    FIELD_NUMBER,
    FIELD_STATUS,
    //
    FIELD_REQUEST_CREATED,
    FIELD_REQUEST_COMPLETED,
    ;

    public Function<String, String> fieldAsCaptionMapper(final LocalizationVariant variant,
	    final Locale locale) {
	return x -> localized(variant, locale) + " '" + x + "'";
    }
}
