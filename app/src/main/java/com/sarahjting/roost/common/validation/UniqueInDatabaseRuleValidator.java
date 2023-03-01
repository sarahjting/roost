package com.sarahjting.roost.common.validation;

import com.sarahjting.roost.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueInDatabaseRuleValidator implements ConstraintValidator<UniqueInDatabase, String> {
    UniqueInDatabase constraintAnnotation;

    @Autowired
    EntityManager entityManager;

    @Override
    public void initialize(UniqueInDatabase constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.constraintAnnotation = constraintAnnotation;
    }

    // https://www.baeldung.com/jpa-and-or-criteria-predicates
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Class entity = constraintAnnotation.entity();
        String attributeName = constraintAnnotation.attributeName();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<?> itemRoot = criteriaQuery.from(entity);

        Predicate predicateForFieldEqualsValue = criteriaBuilder.equal(itemRoot.get(attributeName), s);
        criteriaQuery.where(predicateForFieldEqualsValue);

        try {
            entityManager.createQuery(criteriaQuery).getSingleResult();
            return false;
        } catch(NoResultException e) {
            return true;
        }
    }
}
