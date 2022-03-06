package com.mcgb.varbifikrimbackend.validator;

import com.mcgb.varbifikrimbackend.enums.PaymentTypeEnum;
import com.mcgb.varbifikrimbackend.validator.annotations.PaymentTypeEnumSubset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PaymentTypeEnumValidator implements ConstraintValidator<PaymentTypeEnumSubset, PaymentTypeEnum> {

    private PaymentTypeEnum[] subset;

    @Override
    public void initialize(PaymentTypeEnumSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(PaymentTypeEnum value, ConstraintValidatorContext context) {
        return value != null && Arrays.asList(subset).contains(value);
    }
}
