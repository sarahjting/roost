package com.sarahjting.roost.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserTests {

    @Test
    void givenUser_whenInvalidEmail_shouldFailValidation() {
        User user = new User("invalid-email", "p4$$W0Rd!");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).hasSize(1);
    }

    @Test
    void givenUser_whenInvalidPassword_shouldFailValidation() {
        User user = new User("test@example.com", "password");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).hasSize(1);
    }

    @Test
    void givenUser_whenValid_shouldPassValidation() {
        User user = new User("test@example.com", "p4$$W0Rd!");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations).hasSize(0);
    }
}
