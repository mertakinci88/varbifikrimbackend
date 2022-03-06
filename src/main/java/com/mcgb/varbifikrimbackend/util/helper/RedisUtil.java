package com.mcgb.varbifikrimbackend.util.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RedisUtil {

    @Autowired
    RedisTemplate redisTemplate;

    public String getBlackListedToken(String username, String tokenExpirationDate) {
        return (String) redisTemplate.opsForValue().get(username + "_" + tokenExpirationDate);
    }

    public void setBlackListedToken(String username, String token, Date tokenExpirationDate) {
        redisTemplate.opsForValue().set(username + "_" + tokenExpirationDate.getTime() , token);
        redisTemplate.expireAt(username + "_" + tokenExpirationDate.getTime(), tokenExpirationDate);
    }
}
