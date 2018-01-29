package tech.lapsa.insurance.domain;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.patterns.domain.MyHcEqToStr;

public abstract class Domain implements Localized, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
	return localized(LocalizationVariant.NORMAL, MyHcEqToStr.toStringLocaleOf(this.getClass()));
    }

    @Override
    @Deprecated // to be removed
    public String localized(final LocalizationVariant variant, final Locale locale) {
	return Objects.toString(this);
    }

    @Override
    public final int hashCode() {
	return MyHcEqToStr.hashCode(this);
    }

    @Override
    public final boolean equals(final Object other) {
	return MyHcEqToStr.equals(this, other);
    }

    public void unlazy() {
    }

    // constructor

    protected Domain() {
    }
}
