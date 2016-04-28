package kz.theeurasia.policy.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidDateOfIssueConstraintValidator.class)
public @interface ValidDateOfIssue {
    String message() default "{kz.theeurasia.policy.validator.ValidDateOfIssue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
