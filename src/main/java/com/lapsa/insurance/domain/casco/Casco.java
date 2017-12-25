package com.lapsa.insurance.domain.casco;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Stream;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.lapsa.insurance.domain.BaseEntity;
import com.lapsa.insurance.domain.Domain;
import com.lapsa.insurance.domain.InsuranceProduct;
import com.lapsa.insurance.elements.CascoDeductibleFullRate;
import com.lapsa.insurance.elements.CascoDeductiblePartialRate;

import tech.lapsa.java.commons.function.MyNumbers;
import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.util.MyCurrencies;
import tech.lapsa.javax.validation.NotNullValue;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "CASCO")
@HashCodePrime(131)
public class Casco extends InsuranceProduct {

    private static final long serialVersionUID = 1L;

    // Покрытие только риска ДТП
    @Basic
    @Column(name = "COVER_ROAD_ACCIDENTS")
    private boolean coverRoadAccidents;

    // Покрытие без риска ДТП
    @Basic
    @Column(name = "COVER_NON_ROAD_ACCIDENTS")
    private boolean coverNonRoadAccidents;

    // информация о застрахованном ТС
    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "CASCO_VEHICLE_ID")
    private CascoVehicle insuredVehicle;

    // информация о допущенных к управлению
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "CASCO_ID")
    private List<CascoDriver> insuredDrivers = new ArrayList<>();

    // применяется ли франшиза на частичный ущерб
    @Basic
    @Column(name = "DEDUCTIBLE_PARTIAL_REQUIRED")
    private boolean deductiblePartialRequired;

    // размер франшизы на частичный ущерб
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "DEDUCTIBLE_PARTIAL_RATE")
    @NotNullValue(message = "{com.lapsa.insurance.domain.casco.Casco.deductiblePartialRate.NotNullValue.message}")
    private CascoDeductiblePartialRate deductiblePartialRate;

    // франшиза на гибель/угон
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "DEDUCTIBLE_FULL_RATE")
    @NotNullValue(message = "{com.lapsa.insurance.domain.casco.Casco.deductibleFullRate.NotNullValue.message}")
    private CascoDeductibleFullRate deductibleFullRate;

    // Спец. СТО для ТС до 3-х лет
    // Спец. СТО для ТС от 3-х до 7 лет
    @Basic
    @Column(name = "SPECIAL_SERVICES_STATION_REQUIRED")
    private boolean specialServiceStationRequired;

    // При убытке (ДТП) до 200 000 тенге - без вызова ГАИ, но с обязательным
    // выездом аварийного комиссара на место события (Алматы, Астана и
    // Караганда)
    @Basic
    @Column(name = "NO_POLICE_CALL_REQUIRED")
    private boolean noPoliceCallRequired;

    // При возникновении ДТП не по вине застрахованного франшиза не применяется
    @Basic
    @Column(name = "NO_GUILT_NO_DEDUCTIBLE_REQUIRED")
    private boolean noGuiltNoDeductibleRequired;

    // Сбор документов в Дорожной Полиции компанией от имени клиента
    @Basic
    @Column(name = "HELP_WITH_POLICY_REQUIRED")
    private boolean helpWithPoliceRequired;

    // Покрытие расходов на услуги Эвакуатора, до 10 000 тенге.
    @Basic
    @Column(name = "EVACUATOR_REQUIRED")
    private boolean evacuatorRequired;

    // Предоставление во временное пользование ТС, пока ТС клиента находится на
    // СТО (до 10 дней);
    @Basic
    @Column(name = "REPLACEMENT_CAR_REQUIRED")
    private boolean replacementCarRequired;

    // Действие договора до 1-го страхового случая
    @Basic
    @Column(name = "CONTRACT_ENDS_AFTER_FIRST_CASE")
    private boolean contractEndsAfterFirstCase;

    // Комплексный договор (т.е. автокаско плюс секции ниже):
    // ДГПО ВТС с лимитом 20 000 000 тенге по случаю и в агрегате сверх лимита
    // по ОГПО ВТС
    @Basic
    @Column(name = "THIRD_PARTY_LIABILITY_COVERAGE")
    private boolean thirdPartyLiabilityCoverage;

    // НС для водителя и пассажиров с лимитом 1 000 000 тенге на 1-го
    // застрахованного по случаю и в агрегате
    @Basic
    @Column(name = "DRIVER_AND_PASSENGER_COVERAGE")
    private boolean driverAndPassengerCoverage;

    @Basic
    @Column(name = "DRIVER_AND_PASSENGER_COUNT")
    @NotNullValue
    @Min(message = "{com.lapsa.insurance.domain.casco.Casco.driverAndPassengerCount.Min.message}", value = 1)
    private Integer driverAndPassengerCount;

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.of(getInsuredVehicle()).ifPresent(Domain::unlazy);
	MyOptionals.streamOf(getInsuredDrivers()) //
		.orElseGet(Stream::empty) //
		.filter(MyObjects::nonNull) //
		.forEach(BaseEntity::unlazy);
    }

    public CascoDriver addDriver(final CascoDriver driver) {
	MyObjects.requireNonNull(driver, "Value must not be null");
	if (insuredDrivers == null)
	    insuredDrivers = new ArrayList<>();
	insuredDrivers.add(driver);
	return driver;
    }

    public CascoDriver removeDriver(final CascoDriver driver) {
	MyObjects.requireNonNull(driver, "Value must not be null");
	if (insuredDrivers != null)
	    insuredDrivers.remove(driver);
	return driver;
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(CASCO.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(calculation) //
		.filter(x -> MyNumbers.nonZero(x.getAmount())) //
		.filter(x -> MyObjects.nonNull(x.getCurrency()))
		.map(x -> MyCurrencies.formatAmount(x.getAmount(), x.getCurrency(), locale)) //
		.map(CASCO_COST.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(insuredVehicle)
		.map(x -> insuredVehicle.getCost())
		.filter(MyNumbers::nonZero)
		.map(x -> MyCurrencies.formatAmount(x, Currency.getInstance("KZT"), locale))
		.map(CASCO_CASCO_VEHICLE_COST.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId()) //
		.toString();
    }

    // GENERATED

    public boolean isCoverRoadAccidents() {
	return coverRoadAccidents;
    }

    public CascoVehicle getInsuredVehicle() {
	return insuredVehicle;
    }

    public void setInsuredVehicle(final CascoVehicle insuredVehicle) {
	this.insuredVehicle = insuredVehicle;
    }

    public List<CascoDriver> getInsuredDrivers() {
	return insuredDrivers;
    }

    protected void setInsuredDrivers(final List<CascoDriver> insuredDrivers) {
	this.insuredDrivers = insuredDrivers;
    }

    public void setCoverRoadAccidents(final boolean coverRoadAccidents) {
	this.coverRoadAccidents = coverRoadAccidents;
    }

    public boolean isCoverNonRoadAccidents() {
	return coverNonRoadAccidents;
    }

    public void setCoverNonRoadAccidents(final boolean coverNonRoadAccidents) {
	this.coverNonRoadAccidents = coverNonRoadAccidents;
    }

    public boolean isDeductiblePartialRequired() {
	return deductiblePartialRequired;
    }

    public void setDeductiblePartialRequired(final boolean deductiblePartialRequired) {
	this.deductiblePartialRequired = deductiblePartialRequired;
    }

    public CascoDeductiblePartialRate getDeductiblePartialRate() {
	return deductiblePartialRate;
    }

    public void setDeductiblePartialRate(final CascoDeductiblePartialRate deductiblePartialRate) {
	this.deductiblePartialRate = deductiblePartialRate;
    }

    public CascoDeductibleFullRate getDeductibleFullRate() {
	return deductibleFullRate;
    }

    public void setDeductibleFullRate(final CascoDeductibleFullRate deductibleFullRate) {
	this.deductibleFullRate = deductibleFullRate;
    }

    public boolean isSpecialServiceStationRequired() {
	return specialServiceStationRequired;
    }

    public void setSpecialServiceStationRequired(final boolean specialServiceStationRequired) {
	this.specialServiceStationRequired = specialServiceStationRequired;
    }

    public boolean isNoPoliceCallRequired() {
	return noPoliceCallRequired;
    }

    public void setNoPoliceCallRequired(final boolean noPoliceCallRequired) {
	this.noPoliceCallRequired = noPoliceCallRequired;
    }

    public boolean isNoGuiltNoDeductibleRequired() {
	return noGuiltNoDeductibleRequired;
    }

    public void setNoGuiltNoDeductibleRequired(final boolean noGuiltNoDeductibleRequired) {
	this.noGuiltNoDeductibleRequired = noGuiltNoDeductibleRequired;
    }

    public boolean isHelpWithPoliceRequired() {
	return helpWithPoliceRequired;
    }

    public void setHelpWithPoliceRequired(final boolean helpWithPoliceRequired) {
	this.helpWithPoliceRequired = helpWithPoliceRequired;
    }

    public boolean isEvacuatorRequired() {
	return evacuatorRequired;
    }

    public void setEvacuatorRequired(final boolean evacuatorRequired) {
	this.evacuatorRequired = evacuatorRequired;
    }

    public boolean isReplacementCarRequired() {
	return replacementCarRequired;
    }

    public void setReplacementCarRequired(final boolean replacementCarRequired) {
	this.replacementCarRequired = replacementCarRequired;
    }

    public boolean isContractEndsAfterFirstCase() {
	return contractEndsAfterFirstCase;
    }

    public void setContractEndsAfterFirstCase(final boolean contractEndsAfterFirstCase) {
	this.contractEndsAfterFirstCase = contractEndsAfterFirstCase;
    }

    public boolean isThirdPartyLiabilityCoverage() {
	return thirdPartyLiabilityCoverage;
    }

    public void setThirdPartyLiabilityCoverage(final boolean thirdPartyLiabilityCoverage) {
	this.thirdPartyLiabilityCoverage = thirdPartyLiabilityCoverage;
    }

    public boolean isDriverAndPassengerCoverage() {
	return driverAndPassengerCoverage;
    }

    public void setDriverAndPassengerCoverage(final boolean driverAndPassengerCoverage) {
	this.driverAndPassengerCoverage = driverAndPassengerCoverage;
    }

    public Integer getDriverAndPassengerCount() {
	return driverAndPassengerCount;
    }

    public void setDriverAndPassengerCount(final Integer driverAndPassengerCount) {
	this.driverAndPassengerCount = driverAndPassengerCount;
    }
}
