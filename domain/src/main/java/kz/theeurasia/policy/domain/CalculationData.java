package kz.theeurasia.policy.domain;

public class CalculationData {

    private PolicyTermClass termClass = PolicyTermClass.YEAR;

    private double premiumCost;

    // GENEERATED

    public PolicyTermClass getTermClass() {
	return termClass;
    }

    public void setTermClass(PolicyTermClass termClass) {
	this.termClass = termClass;
    }

    public double getPremiumCost() {
	return premiumCost;
    }

    public void setPremiumCost(double premiumCost) {
	this.premiumCost = premiumCost;
    }

}
