package kz.theeurasia.policy.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = IdNumberConstraintValidator.class)
public @interface IdNumber {

    String message() default "{kz.theeurasia.policy.validator.IdNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}