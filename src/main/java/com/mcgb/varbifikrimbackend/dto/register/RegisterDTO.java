package com.mcgb.varbifikrimbackend.dto.register;

import com.mcgb.varbifikrimbackend.enums.MembershipLengthTypeEnum;
import com.mcgb.varbifikrimbackend.enums.MembershipTypeEnum;
import com.mcgb.varbifikrimbackend.enums.PaymentTypeEnum;
import com.mcgb.varbifikrimbackend.validator.annotations.MembershipTypeEnumSubset;
import com.mcgb.varbifikrimbackend.validator.annotations.MembershipTypeLengthEnumSubset;
import com.mcgb.varbifikrimbackend.validator.annotations.PaymentTypeEnumSubset;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterDTO {
    @NotBlank(message = "Ad boş olamaz.")
    private String name;
    @NotBlank(message = "Soyad boş olamaz.")
    private String surname;
    @NotBlank(message = "Email boş olamaz.")
    @Email(message = "Email adresi geçerli olmalı.")
    private String email;
    @NotBlank(message = "Kullanıcı adı boş olamaz.")
    @Length(min = 5, max = 15, message = "Kullanıcı adı en az 5, en fazla 15 karakter olabilir.")
    private String username;
    @NotBlank(message = "Şifre boş olamaz.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
            message = "Şifre en az 8 karakterden oluşmalı ve içerisinde en az 1 büyük harf en az 1 küçük harf ve 1 rakam içermeli.")
    private String password;
    private String corporateName;
    @MembershipTypeEnumSubset(anyOf = {MembershipTypeEnum.BASIC, MembershipTypeEnum.STANDART, MembershipTypeEnum.SUPER})
    private MembershipTypeEnum membershipType;
    @MembershipTypeLengthEnumSubset(anyOf = {MembershipLengthTypeEnum.SIX_MONTHS, MembershipLengthTypeEnum.ONE_YEAR})
    private MembershipLengthTypeEnum membershipLengthType;
    @PaymentTypeEnumSubset(anyOf = {PaymentTypeEnum.EFT_OR_TRANSFER, PaymentTypeEnum.CREDIT_CART})
    private PaymentTypeEnum paymentType;
}
