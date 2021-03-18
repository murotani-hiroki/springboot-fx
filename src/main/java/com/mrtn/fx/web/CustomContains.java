package com.mrtn.fx.web;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {CustomConstraintValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomContains {
    Class<?>[]groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "com.mrtn.fx.web.CustomContains.message";
    String label();
    String[] values();
}
