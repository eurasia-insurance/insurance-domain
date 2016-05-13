package com.lapsa.insurance.domain.services.beans;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.lapsa.insurance.domain.InsuredDriverData;
import com.lapsa.insurance.domain.InsuredVehicleData;
import com.lapsa.insurance.domain.services.CalculationService;
import com.lapsa.insurance.elements.PolicyTermClass;
import com.lapsa.insurance.elements.services.PremiumCostCalculatorRatesService;

@ApplicationScoped
public class DefaultCalculationService implements CalculationService {

    @Inject
    private PremiumCostCalculatorRatesService rates;

    @Override
    public double calculatePremiumCost(List<InsuredDriverData> drivers, List<InsuredVehicleData> vehicles,
	    PolicyTermClass termClass) {
	double maximumCost = 0d;
	for (InsuredDriverData driver : drivers)
	    for (InsuredVehicleData vehicle : vehicles) {
		double cost = _calculatePremiumCostVariant(driver, vehicle, termClass);
		if (cost == 0) {
		    return 0;
		}
		if (maximumCost < cost)
		    maximumCost = cost;
	    }
	return maximumCost;
    }

    private double _calculatePremiumCostVariant(InsuredDriverData insured, InsuredVehicleData vehicle,
	    PolicyTermClass policyTermClass) {
	double premium = rates.getBase();
	premium *= rates.getBaseRate();
	premium *= rates.getRegionRate(vehicle.getRegion());
	premium *= rates.getIsMajorKZCityCorrectionRate(vehicle.getCity());
	premium *= rates.getVehicleTypeRate(vehicle.getVehicleClass());
	premium *= rates.getDriverExpirienceTypeRate(insured.getAgeClass(), insured.getExpirienceClass());
	premium *= rates.getVehicleAgeTypeRate(vehicle.getVehicleAgeClass());
	premium *= rates.getInsuranceClassTypeRate(insured.getInsuranceClassType());
	premium *= rates.getPolicyTermClassRate(policyTermClass);
	premium *= rates.getPrivilegeRate(insured.isHasAnyPrivilege());
	premium = roundMoney(premium); // округляем до "копеек"
	return premium;
    }

    private static double roundMoney(final double input) {
	double output = input;
	output *= 100d;
	output = Math.round(output);
	output /= 100d;
	return output;
    }
}