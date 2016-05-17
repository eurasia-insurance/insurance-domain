package com.lapsa.insurance.validation;

import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidHumanNameConstraintValidator implements ConstraintValidator<ValidHumanName, String> {

    private static final String ENGLISH_PATTERN = "^[A-Z\\ \\-]+$";
    private static final String RUSSIAN_PATTERN = "^[А-Я\\ \\-]+$";
    private static final String KAZAKH_PATTERN = "^[А-ЯӘҒҚҢӨҰҮҺІ\\ \\-]+$";

    private static Map<String, Pattern> languagePatterns;

    static {
	int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS | Pattern.UNICODE_CASE;
	languagePatterns.put("en", Pattern.compile(ENGLISH_PATTERN, flags));
	languagePatterns.put("ru", Pattern.compile(RUSSIAN_PATTERN, flags));
	languagePatterns.put("kk", Pattern.compile(KAZAKH_PATTERN, flags));
    }

    private String[] languagesAllowed;

    public void initialize(ValidHumanName a) {
	languagesAllowed = a.languagesAllowed();
    }

    public boolean isValid(String value, ConstraintValidatorContext cvc) {
	if (value == null)
	    return true;
	for (String lang : languagesAllowed) {
	    Pattern p = languagePatterns.get(lang);
	    if (p != null && p.matcher(value.toString()).matches())
		return true;
	}
	return false;
    }
}