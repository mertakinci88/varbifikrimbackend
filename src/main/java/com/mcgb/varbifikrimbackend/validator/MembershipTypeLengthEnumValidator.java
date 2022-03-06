package com.mcgb.varbifikrimbackend.validator;

import com.mcgb.varbifikrimbackend.enums.MembershipLengthTypeEnum;
import com.mcgb.varbifikrimbackend.validator.annotations.MembershipTypeLengthEnumSubset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class MembershipTypeLengthEnumValidator implements ConstraintValidator<MembershipTypeLengthEnumSubset, MembershipLengthTypeEnum> {

    private MembershipLengthTypeEnum[] subset;

    @Override
    public void initialize(MembershipTypeLengthEnumSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(MembershipLengthTypeEnum value, ConstraintValidatorContext context) {
        return value != null && Arrays.asList(subset).contains(value);
    }
}
