package com.mcgb.varbifikrimbackend.dto.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RegisterVerifyMailPublisher {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ChannelTopic topic;

    public void publishRegisterVerifyMail(String username, String email, String verifyCode) {
        VerifyMailDTO verifyMailDTO = new VerifyMailDTO();
        verifyMailDTO.setUsername(username);
        verifyMailDTO.setEmail(email);
        verifyMailDTO.setVerifyCode(verifyCode);
        redisTemplate.convertAndSend(topic.getTopic(), verifyMailDTO);
    }

}
