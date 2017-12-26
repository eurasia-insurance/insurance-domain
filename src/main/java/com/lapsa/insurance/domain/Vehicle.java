package com.lapsa.insurance.domain;

import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lapsa.kz.country.KZArea;
import com.lapsa.kz.country.KZCity;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.function.MyStrings;

@Entity
@Table(name = "VEHICLE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle extends EntitySuperclass {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "VIN_CODE")
    protected String vinCode;

    @Basic
    @Column(name = "MODEL_NAME")
    protected String model;

    @Basic
    @Column(name = "MANUFACTURER_NAME")
    protected String manufacturer;

    @Basic
    @Column(name = "COLOR")
    protected String color;

    @Basic
    @Column(name = "VEHICLE_YOM")
    protected Integer yearOfManufacture;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "REGISTRATION_AREA")
    protected KZArea area;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "REGISTRATION_CITY")
    protected KZCity city;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "VEHICLE_CERTIFICATE_ID")
    protected VehicleCertificateData certificateData = new VehicleCertificateData();

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.of(getCertificateData()).ifPresent(Domain::unlazy);
    }

    public String getFullName() {
	final StringJoiner sj = new StringJoiner(" ");

	MyOptionals.of(manufacturer) //
		.map(String::trim) //
		.ifPresent(sj::add);

	MyOptionals.of(manufacturer) //
		.map(x -> model) // model used only in conjuction with first
				 // manufacturer
		.map(String::trim) //
		.ifPresent(sj::add);

	return MyStrings.nullOnEmpty(sj.toString());
    }

    // GENERATED

    public String getVinCode() {
	return vinCode;
    }

    public void setVinCode(final String vinCode) {
	this.vinCode = vinCode;
    }

    public String getModel() {
	return model;
    }

    public void setModel(final String model) {
	this.model = model;
    }

    public String getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(final String manufacturer) {
	this.manufacturer = manufacturer;
    }

    public String getColor() {
	return color;
    }

    public void setColor(final String color) {
	this.color = color;
    }

    public Integer getYearOfManufacture() {
	return yearOfManufacture;
    }

    public void setYearOfManufacture(final Integer yearOfManufacture) {
	this.yearOfManufacture = yearOfManufacture;
    }

    public KZArea getArea() {
	return area;
    }

    public void setArea(final KZArea area) {
	this.area = area;
    }

    public KZCity getCity() {
	return city;
    }

    public void setCity(final KZCity city) {
	this.city = city;
    }

    public VehicleCertificateData getCertificateData() {
	return certificateData;
    }

    public void setCertificateData(final VehicleCertificateData certificateData) {
	this.certificateData = certificateData;
    }
}
