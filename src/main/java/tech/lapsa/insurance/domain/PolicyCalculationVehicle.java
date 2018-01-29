package tech.lapsa.insurance.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.elements.VehicleAgeClass;
import com.lapsa.insurance.elements.VehicleClass;
import com.lapsa.kz.country.KZArea;

import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POLICY_CALCULATION_VEHICLE")
@HashCodePrime(13)
public class PolicyCalculationVehicle extends IntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    // constructor

    protected PolicyCalculationVehicle() {
    }

    // regNumber

    @Basic
    @Column(name = "REG_NUMBER")
    protected VehicleRegNumber regNumber;

    public VehicleRegNumber getRegNumber() {
	return regNumber;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "REGISTRATION_AREA")
    protected KZArea registrationArea;

    public KZArea getRegistrationArea() {
	return registrationArea;
    }

    // vehicleClass

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "VEHICLE_CLASS")
    protected VehicleClass vehicleClass;

    public VehicleClass getVehicleClass() {
	return vehicleClass;
    }

    // vehicleAge

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "AGE_CLASS")
    protected VehicleAgeClass ageClass;

    public VehicleAgeClass getAgeClass() {
	return ageClass;
    }

    @Basic
    @Column(name = "VEHICLE_YOM")
    protected Integer yearOfManufacture;

    public Integer getYearOfManufacture() {
	return yearOfManufacture;
    }

    // temporaryEntry

    @Basic
    @Column(name = "TEMPORARY_ENTRY")
    protected Boolean temporaryEntry;

    public Boolean isTemporaryEntry() {
	return temporaryEntry;
    }

    // majorCity

    @Basic
    @Column(name = "MAJOR_CITY")
    protected Boolean majorCity;

    public Boolean isMajorCity() {
	return majorCity;
    }
}
