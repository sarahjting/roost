package com.sarahjting.roost.common.validation;

import com.sarahjting.roost.user.User;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PasswordRuleValidatorTests {
    @Test
    public void givenPassword_whenValid_thenPassValidation() {
        assertPasswordIsValid("p4$$W0rD!");
    }

    @Test
    public void givenPassword_whenTooShort_thenFailValidation() {
        assertPasswordIsInvalid("pass");
    }

    @Test
    public void givenPassword_whenInsufficientSpecialCharacters_thenFailValidation() {
        assertPasswordIsValid("p4SsWOrD!");
    }

    @Test
    public void givenPassword_whenInsufficientUpperCaseCharacters_thenFailValidation() {
        assertPasswordIsInvalid("password");
    }

    @Test
    public void givenPassword_whenInsufficientNumeric_thenFailValidation() {
        assertPasswordIsValid("pa$$WorD!");
    }

    private void assertPasswordIsValid(String password) {
        assertThat(validate(password)).isEmpty();
    }

    private void assertPasswordIsInvalid(String password) {
        assertThat(validate(password)).isNotEmpty();
    }

    private Set<ConstraintViolation<User>> validate(String password) {
        User user = new User("test@example.com", password);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        return violations;
    }
}
