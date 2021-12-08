package com.example.msorder.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target({TYPE, FIELD, METHOD})
@Constraint(validatedBy = {StartWithCheckImpl.class})
public @interface StartWith {

    String value();

    String message() default "String {value} ile başlamalı";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
