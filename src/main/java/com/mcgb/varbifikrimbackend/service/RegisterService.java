package com.mcgb.varbifikrimbackend.service;

import com.mcgb.varbifikrimbackend.converter.RegisterConverter;
import com.mcgb.varbifikrimbackend.dto.register.*;
import com.mcgb.varbifikrimbackend.entity.*;
import com.mcgb.varbifikrimbackend.enums.PaymentTypeEnum;
import com.mcgb.varbifikrimbackend.enums.UserStatusTypeEnum;
import com.mcgb.varbifikrimbackend.enums.UserMembershipStatusTypeEnum;
import com.mcgb.varbifikrimbackend.repository.UserMembershipRepository;
import com.mcgb.varbifikrimbackend.repository.UserRepository;
import com.mcgb.varbifikrimbackend.util.exception.BusinessException;
import com.mcgb.varbifikrimbackend.util.exception.ExceptionConstants;
import com.mcgb.varbifikrimbackend.util.helper.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterService implements ExceptionConstants {

    @Autowired
    RegisterConverter registerConverter;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMembershipRepository userMembershipRepository;
    @Autowired
    DateUtil dateUtil;
    @Autowired
    RegisterVerifyMailPublisher registerVerifyMailPublisher;

    public RegisterResponseDTO registerNewUser(RegisterDTO registerDTO) {
        User anyUserWithSameUsername = userRepository.findByUsername(registerDTO.getUsername());
        if(anyUserWithSameUsername != null)
            throw new BusinessException(String.format(KULLANICI_ADI_ALINMIS, registerDTO.getUsername()));

        User user = registerConverter.convertRegisterToUser(registerDTO);
        user.setStatus(UserStatusTypeEnum.WAITING_FOR_USERS_VERIFICATION);
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        user.setRecordTime(new Date());
        String verifyUserCode = RandomString.make(64);
        user.setVerifyCode(verifyUserCode);

        Membership membership = new Membership();
        membership.setMembershipType(registerDTO.getMembershipType());
        membership.setMembershipLengthType(registerDTO.getMembershipLengthType());
        membership.setPaymentType(registerDTO.getPaymentType());

        UserMembership userMembership = new UserMembership();
        userMembership.setMembership(membership);
        userMembership.setStatus(registerDTO.getPaymentType().equals(PaymentTypeEnum.EFT_OR_TRANSFER) ? UserMembershipStatusTypeEnum.WAITING_FOR_CONFIRMATION : UserMembershipStatusTypeEnum.ACTIVE);
        if (userMembership.getStatus().equals(UserMembershipStatusTypeEnum.ACTIVE)) {
            userMembership.setStartDate(new Date());
            userMembership.setFinishDate(DateUtils.addMonths(new Date(), dateUtil.convertMembershipTypeLengthToInt(registerDTO.getMembershipLengthType())));
        }
        userMembershipRepository.insert(userMembership);

        user.setUserMembershipId(userMembership.getId());
        userRepository.insert(user);

        registerVerifyMailPublisher.publishRegisterVerifyMail(user.getUsername(), user.getEmail(), user.getVerifyCode());

        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        registerResponseDTO.setMessage("Kayıt işleminizin ilk adımı başarıyla gerçekleşti. " + user.getEmail() + " adresine doğrulama maili gönderildi. Onaylayarak kayıt işlemini tamamlayabilirsiniz.");
        return registerResponseDTO;
    }

    public VerifyUserResponseDTO verifyUser(String code) {
        VerifyUserResponseDTO verifyUserResponseDTO = new VerifyUserResponseDTO();
        User user = userRepository.findByVerifyCode(code);
        if (user == null) {
            verifyUserResponseDTO.setMessage(DOGRULAMA_KODU_HATALI);
        } else {
            user.setStatus(UserStatusTypeEnum.ACTIVE);
            userRepository.save(user);
            verifyUserResponseDTO.setMessage("Hesabınız doğrulandı. Aşağıdaki linkten giriş yapabilirsiniz.");
        }
        return verifyUserResponseDTO;
    }

}
