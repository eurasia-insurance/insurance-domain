package tech.lapsa.insurance.domain;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tech.lapsa.kz.taxpayer.TaxpayerNumber;

@Embeddable
public class PersonalInformation extends Domain {

    private static final long serialVersionUID = 1L;

    // idNumber

    @Basic
    @Column(name = "IDNUMBER")
    protected TaxpayerNumber idNumber;

    public TaxpayerNumber getIdNumber() {
	return idNumber;
    }

    // name

    @Basic
    @Column(name = "NAME")
    protected String name;

    public String getName() {
	return name;
    }

    // firstName

    @Basic
    @Column(name = "FIRST_NAME")
    protected String firstName;

    public String getFirstName() {
	return firstName;
    }

    // surename

    @Basic
    @Column(name = "SURENAME")
    protected String surename;

    public String getSurename() {
	return surename;
    }

    // patronymic

    @Basic
    @Column(name = "PATRONYMIC")
    protected String patronymic;

    public String getPatronymic() {
	return patronymic;
    }

    // dayOfBirth

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DOB")
    protected LocalDate dayOfBirth;

    public LocalDate getDayOfBirth() {
	return dayOfBirth;
    }
}
