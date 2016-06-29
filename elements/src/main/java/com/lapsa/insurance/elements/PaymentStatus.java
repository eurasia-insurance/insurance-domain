package com.lapsa.insurance.elements;

public enum PaymentStatus implements ElementsBundleBase {
    PENDING, // ожидается оплата
    DONE, // оплачено
    //
    ;

    @Override
    public String canonicalName() {
	return String.format("%1$s.%2$s", this.getClass().getName(), name());
    }
}
