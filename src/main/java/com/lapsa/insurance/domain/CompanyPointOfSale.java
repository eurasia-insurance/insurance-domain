package com.lapsa.insurance.domain;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lapsa.international.localization.LocalizationLanguage;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.javax.validation.NotEmptyString;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POS", indexes = { //
	@Index(name = "POS_IDX01", columnList = "IS_AVAILABLE"), //
	@Index(name = "POS_IDX02", columnList = "IS_AVAILABLE,IS_PICKUP_AVAILABLE"), //
	@Index(name = "POS_IDX03", columnList = "IS_AVAILABLE,IS_DELIVERY_SERVICE_AVAILABLE"), //
	@Index(name = "POS_IDX04", columnList = "IS_AVAILABLE,IS_PICKUP_AVAILABLE,IS_DELIVERY_SERVICE_AVAILABLE"), //
	//
})
@HashCodePrime(11)
public class CompanyPointOfSale extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "NAME")
    @NotNullValue
    @NotEmptyString
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "LANGUAGE")
    @Column(name = "VALUE")
    @CollectionTable(name = "POS_NAME_LOCALIZATION", //
	    joinColumns = { @JoinColumn(name = "ENTITY_ID") }, //
	    uniqueConstraints = { @UniqueConstraint(columnNames = { "ENTITY_ID", "LANGUAGE" }) } //
    )
    private Map<LocalizationLanguage, String> nameLocalization = new HashMap<>();

    @Embedded
    private PostAddress address;

    @Embedded
    private GeoPoint geoPoint;

    @Basic
    @Column(name = "IS_AVAILABLE")
    private boolean available;

    @Basic
    @Column(name = "IS_OWN_OFFICE")
    private boolean companyOwnOffice;

    @Basic
    @Column(name = "IS_PICKUP_AVAILABLE")
    private boolean pickupAvailable;

    @Basic
    @Column(name = "IS_DELIVERY_SERVICE_AVAILABLE")
    private boolean deliveryServicesAvailable;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "POS_ID")
    private List<CompanyContactPhone> phones = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "POS_ID")
    private List<CompanyContactEmail> emailAddresses = new ArrayList<>();

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.of(getAddress()).ifPresent(Domain::unlazy);
	getNameLocalization().size();
	getPhones().size();
	getEmailAddresses().size();
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	return MyOptionals.of(nameLocalization.getOrDefault(LocalizationLanguage.byLocale(locale), name)) //
		.map(MyStrings::capitalizeFirstLetter) //
		.orElseGet(() -> COMPANY_POINT_OF_SALE.localized(variant, locale));
    }

    public CompanyContactPhone addPhone(final CompanyContactPhone phone) {
	if (phone == null)
	    throw new NullPointerException("Value must not be null");
	if (phones == null)
	    phones = new ArrayList<>();
	phones.add(phone);
	return phone;
    }

    public CompanyContactPhone removePhone(final CompanyContactPhone phone) {
	if (phone == null)
	    throw new NullPointerException("Value must not be null");
	if (phones != null)
	    phones.remove(phone);
	return phone;
    }

    public CompanyContactEmail addEmailAddress(final CompanyContactEmail phone) {
	if (phone == null)
	    throw new NullPointerException("Value must not be null");
	if (emailAddresses == null)
	    emailAddresses = new ArrayList<>();
	emailAddresses.add(phone);
	return phone;
    }

    public CompanyContactEmail removeEmailAddress(final CompanyContactEmail phone) {
	if (phone == null)
	    throw new NullPointerException("Value must not be null");
	if (emailAddresses != null)
	    emailAddresses.remove(phone);
	return phone;
    }

    // GENERATED

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public Map<LocalizationLanguage, String> getNameLocalization() {
	return nameLocalization;
    }

    protected void setNameLocalization(final Map<LocalizationLanguage, String> nameLocalization) {
	this.nameLocalization = nameLocalization;
    }

    public PostAddress getAddress() {
	return address;
    }

    public void setAddress(final PostAddress address) {
	this.address = address;
    }

    public GeoPoint getGeoPoint() {
	return geoPoint;
    }

    public void setGeoPoint(final GeoPoint geoPoint) {
	this.geoPoint = geoPoint;
    }

    public List<CompanyContactEmail> getEmailAddresses() {
	return emailAddresses;
    }

    protected void setEmailAddresses(final List<CompanyContactEmail> emailAddresses) {
	this.emailAddresses = emailAddresses;
    }

    public boolean isAvailable() {
	return available;
    }

    public void setAvailable(final boolean available) {
	this.available = available;
    }

    public List<CompanyContactPhone> getPhones() {
	return phones;
    }

    protected void setPhones(final List<CompanyContactPhone> phones) {
	this.phones = phones;
    }

    public boolean isCompanyOwnOffice() {
	return companyOwnOffice;
    }

    public void setCompanyOwnOffice(final boolean companyOwnOffice) {
	this.companyOwnOffice = companyOwnOffice;
    }

    public boolean isPickupAvailable() {
	return pickupAvailable;
    }

    public void setPickupAvailable(final boolean pickupAvailable) {
	this.pickupAvailable = pickupAvailable;
    }

    public boolean isDeliveryServicesAvailable() {
	return deliveryServicesAvailable;
    }

    public void setDeliveryServicesAvailable(final boolean deliveryServicesAvailable) {
	this.deliveryServicesAvailable = deliveryServicesAvailable;
    }
}
