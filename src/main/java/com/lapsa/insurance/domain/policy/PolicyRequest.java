package com.lapsa.insurance.domain.policy;

import static com.lapsa.insurance.domain.DisplayNameElements.*;

import java.util.Locale;
import java.util.StringJoiner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lapsa.insurance.domain.BaseEntity;
import com.lapsa.insurance.domain.InsuranceProduct;
import com.lapsa.insurance.domain.InsuranceRequest;
import com.lapsa.insurance.elements.InsuranceProductType;
import com.lapsa.insurance.elements.RequestSource;

import tech.lapsa.java.commons.function.MyOptionals;
import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.localization.Localizeds;
import tech.lapsa.patterns.domain.HashCodePrime;

@Entity
@Table(name = "POLICY_REQUEST")
@HashCodePrime(83)
public class PolicyRequest extends InsuranceRequest {

    private static final long serialVersionUID = 1L;

    public PolicyRequest() {
    }

    public PolicyRequest(final RequestSource source) {
	super(source);
    }

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "POLICY_ID")
    private Policy policy = new Policy();

    @Override
    public void unlazy() {
	super.unlazy();
	MyOptionals.of(getPolicy()).ifPresent(BaseEntity::unlazy);
    }

    @Override
    public InsuranceProduct getProduct() {
	return policy;
    }

    @Override
    public InsuranceProductType getProductType() {
	return InsuranceProductType.POLICY;
    }

    @Override
    public String localized(final LocalizationVariant variant, final Locale locale) {
	final StringBuilder sb = new StringBuilder();

	sb.append(POLICY_REQUEST.localized(variant, locale));

	final StringJoiner sj = new StringJoiner(", ", " ", "");
	sj.setEmptyValue("");

	MyOptionals.of(id)
		.map(String::valueOf)
		.map(FIELD_NUMBER.fieldAsCaptionMapper(variant, locale))//
		.ifPresent(sj::add);

	MyOptionals.of(created) //
		.map(Localizeds.instantMapper(locale)) //
		.map(FIELD_REQUEST_CREATED.fieldAsCaptionMapper(variant, locale)) //
		.ifPresent(sj::add);

	MyOptionals.of(progressStatus)
		.map(Localized.toLocalizedMapper(variant, locale))
		.map(FIELD_STATUS.fieldAsCaptionMapper(variant, locale))
		.ifPresent(sj::add);

	return sb.append(sj.toString()) //
		.append(appendEntityId())
		.toString();
    }

    // GENERATED

    public Policy getPolicy() {
	return policy;
    }

    public void setPolicy(final Policy policy) {
	this.policy = policy;
    }
}
