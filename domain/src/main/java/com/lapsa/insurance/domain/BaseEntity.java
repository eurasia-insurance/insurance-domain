package com.lapsa.insurance.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.lapsa.commons.function.MyOptionals;

public abstract class BaseEntity<T> extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 2914122165051543297L;

    protected T id;

    @Override
    public int hashCode() {
	HashCodeBuilder hcb = new HashCodeBuilder();
	if (id == null)
	    return hcb.appendSuper(super.hashCode()).toHashCode();
	return hcb.append(id).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
	if (other == null || other.getClass() != getClass())
	    return false;
	if (other == this)
	    return true;

	@SuppressWarnings("unchecked")
	BaseEntity<T> that = (BaseEntity<T>) other;
	EqualsBuilder eb = new EqualsBuilder();
	if (id == null)
	    return eb.appendSuper(super.equals(that)).isEquals();
	return eb.append(id, that.id).isEquals();
    }

    //

    protected String appendEntityId() {
	return " [ID=" + MyOptionals.of(id).map(Object::toString).orElse("NONE") + "]";
    }

    // GENERATED

    public T getId() {
	return id;
    }

    protected void setId(T id) {
	this.id = id;
    }

}
