package com.mcgb.varbifikrimbackend.entity;

import com.mcgb.varbifikrimbackend.enums.UserStatusTypeEnum;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users")
@TypeAlias(value = "user")
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String name;
    private String surname;
    private String corporateName;
    private String email;
    private String password;
    private UserStatusTypeEnum status;
    private Date recordTime;
    private Date updateTime;
    private ObjectId userMembershipId;
    private String verifyCode;
}
