package com.mcgb.varbifikrimbackend.dto.register;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifyMailDTO implements Serializable {
    private String username;
    private String email;
    private String verifyCode;
}
