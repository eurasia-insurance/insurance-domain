package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lapsa.insurance.elements.CascoDeductibleFullRate;
import com.lapsa.insurance.elements.CascoDeductiblePartialRate;

import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "CASCO_CALCULATION")
@DiscriminatorValue("CASCO_CALCULATION")
@HashCodePrime(31)
public class CascoCalculation extends Calculation {

    protected static final long serialVersionUID = 1L;

    // coverRoadAccidents
    // Покрытие только риска ДТП

    @Basic
    @Column(name = "COVER_ROAD_ACCIDENTS")
    protected Boolean coverRoadAccidents;

    public Boolean isCoverRoadAccidents() {
	return coverRoadAccidents;
    }

    // coverNonRoadAccidents
    // Покрытие без риска ДТП

    @Basic
    @Column(name = "COVER_NON_ROAD_ACCIDENTS")
    protected Boolean coverNonRoadAccidents;

    public Boolean isCoverNonRoadAccidents() {
	return coverNonRoadAccidents;
    }

    // vehicle
    // информация о застрахованном ТС

    @OneToOne(fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "CASCO_CALCULATION_ID")
    protected CascoCalculationVehicle vehicle;

    public CascoCalculationVehicle getVehicle() {
	return vehicle;
    }

    // deductiblePartialRequired
    // применяется ли франшиза на частичный ущерб

    @Basic
    @Column(name = "DEDUCTIBLE_PARTIAL_REQUIRED")
    protected Boolean deductiblePartialRequired;

    public Boolean isDeductiblePartialRequired() {
	return deductiblePartialRequired;
    }

    // deductiblePartialRate
    // размер франшизы на частичный ущерб

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "DEDUCTIBLE_PARTIAL_RATE")
    protected CascoDeductiblePartialRate deductiblePartialRate;

    public CascoDeductiblePartialRate getDeductiblePartialRate() {
	return deductiblePartialRate;
    }

    // deductibleFullRate
    // франшиза на гибель/угон

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "DEDUCTIBLE_FULL_RATE")
    protected CascoDeductibleFullRate deductibleFullRate;

    public CascoDeductibleFullRate getDeductibleFullRate() {
	return deductibleFullRate;
    }

    // specialServiceStationRequired
    // Спец. СТО для ТС до 3-х лет
    // Спец. СТО для ТС от 3-х до 7 лет

    @Basic
    @Column(name = "SPECIAL_SERVICES_STATION_REQUIRED")
    protected Boolean specialServiceStationRequired;

    public Boolean isSpecialServiceStationRequired() {
	return specialServiceStationRequired;
    }

    // noPoliceCallRequired
    // При убытке (ДТП) до 200 000 тенге - без вызова ГАИ, но с обязательным
    // выездом аварийного комиссара на место события (Алматы, Астана и
    // Караганда)

    @Basic
    @Column(name = "NO_POLICE_CALL_REQUIRED")
    protected Boolean noPoliceCallRequired;

    public Boolean isNoPoliceCallRequired() {
	return noPoliceCallRequired;
    }

    // noGuiltNoDeductibleRequired
    // При возникновении ДТП не по вине застрахованного франшиза не применяется

    @Basic
    @Column(name = "NO_GUILT_NO_DEDUCTIBLE_REQUIRED")
    protected Boolean noGuiltNoDeductibleRequired;

    public Boolean isNoGuiltNoDeductibleRequired() {
	return noGuiltNoDeductibleRequired;
    }

    // helpWithPoliceRequired
    // Сбор документов в Дорожной Полиции компанией от имени клиента

    @Basic
    @Column(name = "HELP_WITH_POLICY_REQUIRED")
    protected Boolean helpWithPoliceRequired;

    public Boolean isHelpWithPoliceRequired() {
	return helpWithPoliceRequired;
    }

    // evacuatorRequired
    // Покрытие расходов на услуги Эвакуатора, до 10 000 тенге.

    @Basic
    @Column(name = "EVACUATOR_REQUIRED")
    protected Boolean evacuatorRequired;

    public Boolean isEvacuatorRequired() {
	return evacuatorRequired;
    }

    // replacementCarRequired
    // Предоставление во временное пользование ТС, пока ТС клиента находится на
    // СТО (до 10 дней);

    @Basic
    @Column(name = "REPLACEMENT_CAR_REQUIRED")
    protected Boolean replacementCarRequired;

    public Boolean isReplacementCarRequired() {
	return replacementCarRequired;
    }

    // contractEndsAfterFirstCase
    // Действие договора до 1-го страхового случая

    @Basic
    @Column(name = "CONTRACT_ENDS_AFTER_FIRST_CASE")
    protected Boolean contractEndsAfterFirstCase;

    public Boolean isContractEndsAfterFirstCase() {
	return contractEndsAfterFirstCase;
    }

    // thirdPartyLiabilityCoverage
    // Комплексный договор (т.е. автокаско плюс секции ниже):
    // ДГПО ВТС с лимитом 20 000 000 тенге по случаю и в агрегате сверх лимита
    // по ОГПО ВТС

    @Basic
    @Column(name = "THIRD_PARTY_LIABILITY_COVERAGE")
    protected Boolean thirdPartyLiabilityCoverage;

    public Boolean isThirdPartyLiabilityCoverage() {
	return thirdPartyLiabilityCoverage;
    }

    // driverAndPassengerCoverage
    // НС для водителя и пассажиров с лимитом 1 000 000 тенге на 1-го
    // застрахованного по случаю и в агрегате

    @Basic
    @Column(name = "DRIVER_AND_PASSENGER_COVERAGE")
    protected Boolean driverAndPassengerCoverage;

    public Boolean isDriverAndPassengerCoverage() {
	return driverAndPassengerCoverage;
    }

    // driverAndPassengerCount

    @Basic
    @Column(name = "DRIVER_AND_PASSENGER_COUNT")
    protected Integer driverAndPassengerCount;

    public Integer getDriverAndPassengerCount() {
	return driverAndPassengerCount;
    }
}
