package com.mrtn.fx.web;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CustomConstraintValidator implements ConstraintValidator<CustomContains, Object> {

    private String message;
    private String label;
    private List<String> values;

    @Override
    public void initialize(CustomContains constraintAnnotation) {
        message = constraintAnnotation.message();
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
