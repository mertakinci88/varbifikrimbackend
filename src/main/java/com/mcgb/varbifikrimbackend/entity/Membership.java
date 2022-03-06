package com.mcgb.varbifikrimbackend.entity;

import com.mcgb.varbifikrimbackend.enums.MembershipLengthTypeEnum;
import com.mcgb.varbifikrimbackend.enums.MembershipTypeEnum;
import com.mcgb.varbifikrimbackend.enums.PaymentTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@TypeAlias(value = "membership")
public class Membership {
    private MembershipTypeEnum membershipType;
    private MembershipLengthTypeEnum membershipLengthType;
    private PaymentTypeEnum paymentType;
}
