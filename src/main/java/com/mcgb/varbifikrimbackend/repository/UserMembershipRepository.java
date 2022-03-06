package com.mcgb.varbifikrimbackend.repository;

import com.mcgb.varbifikrimbackend.entity.UserMembership;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserMembershipRepository extends MongoRepository<UserMembership, ObjectId> {
}
