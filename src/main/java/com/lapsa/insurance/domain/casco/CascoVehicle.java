package com.lapsa.insurance.domain.casco;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.lapsa.insurance.domain.Image;
import com.lapsa.insurance.domain.Vehicle;
import com.lapsa.insurance.domain.VehicleCertificateData;
import com.lapsa.insurance.elements.CascoCarAgeClass;

import tech.lapsa.java.commons.function.MyFunctions;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.util.MyCurrencies;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.javax.validation.NotTooOldYearOfIssue;
import tech.lapsa.javax.validation.NotTooYoungYearOfIssue;
import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "CASCO_VEHICLE")
@HashCodePrime(157)
public class CascoVehicle extends Vehicle {

    private static final long serialVersionUID = 1L;

    @Basic
    @Column(name = "VEHICLE_COST")
    @NotNullValue
    @Min(message = "{com.lapsa.insurance.domain.casco.CascoVehicle.cost.Min.message}", value = 1000000)
    @Max(message = "{com.lapsa.insurance.domain.casco.CascoVehicle.cost.Max.message}", value = 52500000)
    private Double cost;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "VEHICLE_CAR_AGE_CLASS")
    @NotNullValue
    private CascoCarAgeClass carAgeClass;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "VIEW1_IMAGE_ID")
    // @MaxImageFileSize(measure = FileSizeMeasure.MB, value = 10)
    private Image view1;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "VIEW2_IMAGE_ID")
    // @MaxImageFileSize(measure = FileSizeMeasure.MB, value = 10)
    private Image view2;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "VIEW3_IMAGE_ID")
    // @MaxImageFileSize(measure = FileSizeMeasure.MB, value = 10)
    private Image view3;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "VIEW4_IMAGE_ID")
    // @MaxImageFileSize(measure = FileSizeMeasure.MB, value = 10)
    private Image view4;

    @Override
    public void unlazy() {
	super.unlazy();
	Arrays.asList(getView1(), getView2(), getView3(), getView4()) //
		.stream() //
		.filter(MyObjects::nonNull) //
		.map(Image::getContent) //
		.forEach(MyFunctions.voidConsumer());
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(MyOptionals.of(getFullName()) //
		.orElseGet(() -> CASCO_VEHICLE.localized(variant, locale)));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(cost)
		.map(x -> MyCurrencies.formatAmount(x, Currency.getInstance("KZT"), locale))
		.map(CASCO_VEHICLE_COST.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(carAgeClass) //
		.map(Localized.toLocalizedMapper(variant, locale)) //
		.map(CASCO_VEHICLE_AGE_CLASS.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	MyOptionals.of(certificateData) //
		.map(VehicleCertificateData::getRegistrationNumber) //
		.map(VehicleRegNumber::getNumber) //
		.map(POLICY_VEHICLE_REG_NUMBER.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public Double getCost() {
	return cost;
    }

    public void setCost(final Double cost) {
	this.cost = cost;
    }

    @NotNullValue
    @NotTooOldYearOfIssue(message = "{com.lapsa.insurance.domain.casco.CascoVehicle.yearOfManufacture.NotTooOldYearOfIssue.message}", maxAge = 10)
    @NotTooYoungYearOfIssue(message = "{com.lapsa.insurance.domain.casco.CascoVehicle.yearOfManufacture.NotTooYoungYearOfIssue.message}", minAge = 0)
    @Override
    public Integer getYearOfManufacture() {
	return super.getYearOfManufacture();
    }

    public CascoCarAgeClass getCarAgeClass() {
	return carAgeClass;
    }

    public void setCarAgeClass(final CascoCarAgeClass carAgeClass) {
	this.carAgeClass = carAgeClass;
    }

    public Image getView1() {
	return view1;
    }

    public void setView1(final Image view1) {
	this.view1 = view1;
    }

    public Image getView2() {
	return view2;
    }

    public void setView2(final Image view2) {
	this.view2 = view2;
    }

    public Image getView3() {
	return view3;
    }

    public void setView3(final Image view3) {
	this.view3 = view3;
    }

    public Image getView4() {
	return view4;
    }

    public void setView4(final Image view4) {
	this.view4 = view4;
    }
}
