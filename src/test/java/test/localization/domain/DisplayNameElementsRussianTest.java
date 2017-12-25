package test.localization.domain;

import com.lapsa.insurance.domain.DisplayNameElements;

import test.localization.ElementsLocalizationTest;

public class DisplayNameElementsRussianTest extends ElementsLocalizationTest<DisplayNameElements> {

    public DisplayNameElementsRussianTest() {
	super(DisplayNameElements.values(), DisplayNameElements.class, LOCALE_RUSSIAN);
    }
}
