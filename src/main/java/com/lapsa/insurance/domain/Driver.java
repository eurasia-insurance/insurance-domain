package com.lapsa.insurance.domain;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.kz.taxpayer.validators.ValidTaxpayerNumber;

@Entity
@Table(name = "DRIVER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Driver extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "ID_NUMBER")
    @NotNullValue
    @ValidTaxpayerNumber
    protected TaxpayerNumber idNumber;

    @Transient
    protected String taxPayerNumber;

    @Transient
    protected PersonalData personalData = new PersonalData();

    @Transient
    protected OriginData originData = new OriginData();

    @Transient
    protected ResidenceData residenceData = new ResidenceData();

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "IDENTITY_CARD_ID")
    protected IdentityCardData identityCardData = new IdentityCardData();

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "DRIVER_LICENSE_ID")
    protected DriverLicenseData driverLicenseData = new DriverLicenseData();

    @Transient
    protected ContactData contactData = new ContactData();

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.of(getDriverLicenseData()).ifPresent(Domain::unlazy);
    }

    // GENERATED

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    public void setIdNumber(final TaxpayerNumber idNumber) {
	this.idNumber = idNumber;
    }

    public String getTaxPayerNumber() {
	return taxPayerNumber;
    }

    public void setTaxPayerNumber(final String taxPayerNumber) {
	this.taxPayerNumber = taxPayerNumber;
    }

    public PersonalData getPersonalData() {
	return personalData;
    }

    public void setPersonalData(final PersonalData personalData) {
	this.personalData = personalData;
    }

    public OriginData getOriginData() {
	return originData;
    }

    public void setOriginData(final OriginData originData) {
	this.originData = originData;
    }

    public ResidenceData getResidenceData() {
	return residenceData;
    }

    public void setResidenceData(final ResidenceData residenceData) {
	this.residenceData = residenceData;
    }

    public IdentityCardData getIdentityCardData() {
	return identityCardData;
    }

    public void setIdentityCardData(final IdentityCardData identityCardData) {
	this.identityCardData = identityCardData;
    }

    public DriverLicenseData getDriverLicenseData() {
	return driverLicenseData;
    }

    public void setDriverLicenseData(final DriverLicenseData driverLicenseData) {
	this.driverLicenseData = driverLicenseData;
    }

    public ContactData getContactData() {
	return contactData;
    }

    public void setContactData(final ContactData contactData) {
	this.contactData = contactData;
    }
}
