package com.mcgb.varbifikrimbackend.validator.annotations;

import com.mcgb.varbifikrimbackend.enums.PaymentTypeEnum;
import com.mcgb.varbifikrimbackend.validator.PaymentTypeEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PaymentTypeEnumValidator.class)
public @interface PaymentTypeEnumSubset {
    PaymentTypeEnum[] anyOf();

    String message() default "Ödeme tipi {anyOf} seçeneklerinden biri olmalıdır.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
