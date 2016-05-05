package com.lapsa.insurance.test.messages;

import com.lapsa.insurance.elements.InsuredExpirienceClass;

public class InsuredExpirienceClassMessagesBundleTest extends EnumTypeMessagesBundleTest<InsuredExpirienceClass>{

    @Override
    protected InsuredExpirienceClass[] getAllEnumValues() {
	return InsuredExpirienceClass.values();
    }

    @Override
    protected String getBundleBaseName() {
	return InsuredExpirienceClass.BUNDLE_BASENAME;
    }
}
