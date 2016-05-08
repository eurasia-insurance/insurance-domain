package kz.theeurasia.policy.validator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DaysBeforeNowConstraintValidator implements ConstraintValidator<DaysBeforeNow, Date> {

    private Mode mode;
    private long days;

    @Override
    public void initialize(DaysBeforeNow constraintAnnotation) {
	this.mode = constraintAnnotation.mode();
	this.days = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;
	switch (mode) {
	case MUST_NOT:
	    return value.toInstant().isBefore(Instant.now().plus(days, ChronoUnit.DAYS));
	case MUST:
	default:
	    return value.toInstant().isBefore(Instant.now().minus(days, ChronoUnit.DAYS));
	}
    }

}