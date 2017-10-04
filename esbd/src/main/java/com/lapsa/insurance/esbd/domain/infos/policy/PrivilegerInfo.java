package com.lapsa.insurance.esbd.domain.infos.policy;

import java.util.Calendar;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PrivilegerInfo {

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    private String type;
    private String certificateNumber;
    private Calendar certificateDateOfIssue;

    // GENERATED

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getCertificateNumber() {
	return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
	this.certificateNumber = certificateNumber;
    }

    public Calendar getCertificateDateOfIssue() {
	return certificateDateOfIssue;
    }

    public void setCertificateDateOfIssue(Calendar certificateDateOfIssue) {
	this.certificateDateOfIssue = certificateDateOfIssue;
    }

}
