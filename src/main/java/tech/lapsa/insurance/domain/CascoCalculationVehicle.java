package tech.lapsa.insurance.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.elements.CascoCarAgeClass;

import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "CASCO_CALCULATION_VEHICLE")
@HashCodePrime(37)
public class CascoCalculationVehicle extends IntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    // cost

    @Basic
    @Column(name = "VEHICLE_COST")
    protected Double cost;

    public Double getCost() {
	return cost;
    }

    // carAgeClass

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "VEHICLE_CAR_AGE_CLASS")
    protected CascoCarAgeClass carAgeClass;

    public CascoCarAgeClass getCarAgeClass() {
	return carAgeClass;
    }
}
