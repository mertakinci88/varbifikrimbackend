package com.mcgb.varbifikrimbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {
    @NotBlank(message = "Kullanıcı adı boş olamaz.")
    private String username;
    @NotBlank(message = "Şifre boş olamaz.")
    private String password;
}
