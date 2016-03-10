package kz.theeurasia.policy.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lapsa.country.Country;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidCountryConstraintValidator.class)
public @interface ValidCountry {

    Country[] invalidValues() default {};

    String message() default "{kz.theeurasia.policy.validator.ValidCountry.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
