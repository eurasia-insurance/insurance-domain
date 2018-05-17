package tech.lapsa.insurance.domain;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POLICY_CALCULATION")
@DiscriminatorValue("POLICY_CALCULATION")
@HashCodePrime(7)
public class PolicyCalculation extends Calculation {

    private static final long serialVersionUID = 1L;

    // constructor

    protected PolicyCalculation() {
    }

    // drivers

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "POLICY_CALCULATION_ID")
    protected List<PolicyCalculationDriver> drivers;

    public List<PolicyCalculationDriver> getDrivers() {
	return MyObjects.nullOrGet(drivers, Collections::unmodifiableList);
    }

    // vehicles

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "POLICY_CALCULATION_ID")
    protected List<PolicyCalculationVehicle> vehicles;

    public List<PolicyCalculationVehicle> getVehicles() {
	return MyObjects.nullOrGet(vehicles, Collections::unmodifiableList);
    }
}