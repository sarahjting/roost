package com.sarahjting.roost.storage.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// this is a class-level validation

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StorageDtoValidator.class)
public @interface StorageDtoAnnotation {
    String message() default "Invalid storage provided.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
