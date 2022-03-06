package com.mcgb.varbifikrimbackend.entity;

import com.mcgb.varbifikrimbackend.enums.UserMembershipStatusTypeEnum;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "usermemberships")
@TypeAlias(value = "userMembership")
public class UserMembership {
    @Id
    private ObjectId id;
    private Membership membership;
    private UserMembershipStatusTypeEnum status;
    private Date startDate;
    private Date finishDate;
}
