package com.mcgb.varbifikrimbackend.validator.annotations;

import com.mcgb.varbifikrimbackend.enums.MembershipTypeEnum;
import com.mcgb.varbifikrimbackend.validator.MembershipTypeEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MembershipTypeEnumValidator.class)
public @interface MembershipTypeEnumSubset {
    MembershipTypeEnum[] anyOf();

    String message() default "Üyelik tipi {anyOf} seçeneklerinden biri olmalıdır.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
