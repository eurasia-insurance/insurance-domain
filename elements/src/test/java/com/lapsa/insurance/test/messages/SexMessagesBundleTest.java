package com.lapsa.insurance.test.messages;

import com.lapsa.insurance.elements.Sex;

public class SexMessagesBundleTest extends EnumTypeMessagesBundleTest<Sex> {

    @Override
    protected Sex[] getAllEnumValues() {
	return Sex.values();
    }

    @Override
    protected String getBundleBaseName() {
	return Sex.BUNDLE_BASENAME;
    }
}
