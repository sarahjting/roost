package com.sarahjting.roost.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordRuleValidator implements ConstraintValidator<Password, String> {
    private static final int MIN_COMPLEX_RULES = 2;
    private static final int MAX_REPETITIVE_CHARS = 3;
    private static final int MIN_SPECIAL_CASE_CHARS = 1;
    private static final int MIN_UPPER_CASE_CHARS = 1;
    private static final int MIN_LOWER_CASE_CHARS = 1;
    private static final int MIN_DIGIT_CASE_CHARS = 1;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(8, Integer.MAX_VALUE));
        CharacterCharacteristicsRule characterCharacteristicsRule = new CharacterCharacteristicsRule(
            MIN_COMPLEX_RULES,
            new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
            new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
            new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
            new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS)
        );
        passwordRules.add(characterCharacteristicsRule);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS));

        PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(s);
        RuleResult ruleResult = passwordValidator.validate(passwordData);

        return ruleResult.isValid();
    }
}
