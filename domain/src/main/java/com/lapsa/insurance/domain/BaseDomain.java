package com.lapsa.insurance.domain;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import tech.lapsa.java.commons.localization.Localized;

public abstract class BaseDomain implements Serializable, Localized {
    private static final long serialVersionUID = 1L;

    protected transient final UUID instanceUUID = UUID.randomUUID();
    protected transient final String instanceWebSafeUUID = "UUID" + instanceUUID.toString().replace("-", "");

    protected abstract int getPrime();

    protected abstract int getMultiplier();

    @Override
    public String toString() {
	return displayName();
    }

    @Override
    public int hashCode() {
	return new HashCodeBuilder(getPrime(), getMultiplier())
		.append(instanceUUID)
		.toHashCode();
    }

    @Override
    public boolean equals(Object other) {
	if (other == null || other.getClass() != getClass())
	    return false;
	if (other == this)
	    return true;
	BaseDomain that = (BaseDomain) other;
	return new EqualsBuilder()
		.append(instanceUUID, that.instanceUUID)
		.isEquals();
    }

    public final UUID getInstanceUUID() {
	return instanceUUID;
    }

    public final String getInstanceWebSafeUUID() {
	return instanceWebSafeUUID;
    }

}
