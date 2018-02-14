package tech.lapsa.insurance.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;

import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POLICY_CALCULATION_DRIVER")
@HashCodePrime(17)
public class PolicyCalculationDriver extends IntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    // constructor

    protected PolicyCalculationDriver() {
    }

    // personal

    @Embedded
    protected PersonalInformation personal;

    public PersonalInformation getPersonal() {
	return personal;
    }

    // insuranceClassType

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "INSURANCE_CLASS")
    protected InsuranceClassType insuranceClassType;

    public InsuranceClassType getInsuranceClassType() {
	return insuranceClassType;
    }

    // ageClass

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "AGE_CLASS")
    protected InsuredAgeClass ageClass;

    public InsuredAgeClass getAgeClass() {
	return ageClass;
    }

    // expirienceClass

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "EXPIRIENCE_CLASS")
    protected InsuredExpirienceClass expirienceClass;

    public InsuredExpirienceClass getExpirienceClass() {
	return expirienceClass;
    }

    // hasAnyPrivilege

    @Basic
    @Column(name = "IS_HAS_ANY_PRIVILEGE")
    private Boolean hasAnyPrivilege;

    public Boolean isHasAnyPrivilege() {
	return hasAnyPrivilege;
    }
}
