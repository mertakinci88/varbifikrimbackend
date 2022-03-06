package com.mcgb.varbifikrimbackend.validator.annotations;

import com.mcgb.varbifikrimbackend.enums.MembershipLengthTypeEnum;
import com.mcgb.varbifikrimbackend.validator.MembershipTypeLengthEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MembershipTypeLengthEnumValidator.class)
public @interface MembershipTypeLengthEnumSubset {
    MembershipLengthTypeEnum[] anyOf();

    String message() default "Üyelik süre tipi {anyOf} seçeneklerinden biri olmalıdır.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
