package tech.lapsa.insurance.domain;

import java.time.LocalDate;
import java.util.Locale;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lapsa.insurance.elements.InsuranceClassType;
import com.lapsa.insurance.elements.InsuredAgeClass;
import com.lapsa.insurance.elements.InsuredExpirienceClass;

import tech.lapsa.kz.taxpayer.TaxpayerNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POLICY_CALCULATION_DRIVER")
@HashCodePrime(7)
public class PolicyCalculationDriver extends IntIdEntitySuperclass {

    private static final long serialVersionUID = 1L;

    // constructor

    protected PolicyCalculationDriver() {
    }

    // idNumber

    @Basic
    @Column(name = "ID_NUMBER")
    protected TaxpayerNumber idNumber;

    public TaxpayerNumber getIdNumber() {
	return idNumber;
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

    // name

    @Basic
    @Column(name = "NAME")
    private String name;

    public String getName() {
	return name;
    }

    // surename

    @Basic
    @Column(name = "SURENAME")
    private String surename;

    public String getSurename() {
	return surename;
    }

    // patronymic

    private String patronymic;

    public String getPatronymic() {
	return patronymic;
    }

    // dayOfBirth

    private LocalDate dayOfBirth;

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }

    // other

    @Override
    public String localized(LocalizationVariant variant, Locale locale) {
	// TODO Auto-generated method stub
	return null;
    }

}
