package com.mcgb.varbifikrimbackend.validator;

import com.mcgb.varbifikrimbackend.enums.MembershipTypeEnum;
import com.mcgb.varbifikrimbackend.validator.annotations.MembershipTypeEnumSubset;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class MembershipTypeEnumValidator implements ConstraintValidator<MembershipTypeEnumSubset, MembershipTypeEnum> {

    private MembershipTypeEnum[] subset;

    @Override
    public void initialize(MembershipTypeEnumSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(MembershipTypeEnum value, ConstraintValidatorContext context) {
        return value != null && Arrays.asList(subset).contains(value);
    }
}
