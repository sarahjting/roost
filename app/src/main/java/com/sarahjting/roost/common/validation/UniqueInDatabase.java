package com.sarahjting.roost.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueInDatabaseRuleValidator.class)
public @interface UniqueInDatabase {
    String message() default "This has already been used.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class entity();

    String attributeName();
}
