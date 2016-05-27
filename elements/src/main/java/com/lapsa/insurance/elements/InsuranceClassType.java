package com.lapsa.insurance.elements;

public enum InsuranceClassType implements ElementsBundleBase {
    CLASS_0,
    CLASS_1,
    CLASS_2,
    CLASS_3,
    CLASS_4,
    CLASS_5,
    CLASS_6,
    CLASS_7,
    CLASS_8,
    CLASS_9,
    CLASS_10,
    CLASS_11,
    CLASS_12,
    CLASS_13,
    CLASS_M,
    //
    ;

    @Override
    public String canonicalName() {
	return String.format("%1$s.%2$s", this.getClass().getName(), name());
    }
}
