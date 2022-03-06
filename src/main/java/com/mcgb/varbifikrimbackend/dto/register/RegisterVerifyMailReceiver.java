package com.mcgb.varbifikrimbackend.dto.register;

import com.mcgb.varbifikrimbackend.service.MailService;
import com.mcgb.varbifikrimbackend.util.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
public class RegisterVerifyMailReceiver implements MessageListener {

    @Autowired
    MailService mailService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(message.getBody()));
            VerifyMailDTO verifyMailDTO = (VerifyMailDTO) objectInputStream.readObject();
            mailService.sendVerificationEmail(verifyMailDTO.getUsername(), verifyMailDTO.getEmail(), verifyMailDTO.getVerifyCode());
        } catch (IOException | ClassNotFoundException | MessagingException e) {
            throw new BusinessException("Doğrulama maili gönderilirken bir hata oluştu: " + e.getMessage());
        }
    }

}
