package com.mcgb.varbifikrimbackend.dto.register;

import com.mcgb.varbifikrimbackend.enums.MembershipTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterResponseDTO {
    private String message;
    private Date memberShipStartDate;
    private Date memberShipFinishDate;
    private MembershipTypeEnum membershipType;
    private Integer numberOfMonthsForMembership;
}
