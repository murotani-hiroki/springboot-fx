package com.mrtn.fx.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

// カスタムバリデーター
public class CustomConstraintValidator implements ConstraintValidator<CustomContains, Object> {

    private String label;
    private List<String> values;

    @Override
    public void initialize(CustomContains constraintAnnotation) {
        label = constraintAnnotation.label();
        values = Arrays.asList(constraintAnnotation.values());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (values.contains(value)) {
            return true;
        }

        context.unwrap(HibernateConstraintValidatorContext.class)
                .addMessageParameter("label", label)
                .addMessageParameter("value", value);

        return false;
    }
}
