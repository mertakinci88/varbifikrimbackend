package com.mcgb.varbifikrimbackend.repository;

import com.mcgb.varbifikrimbackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByVerifyCode(String verifyCode);
}