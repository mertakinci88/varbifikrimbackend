package com.mcgb.varbifikrimbackend.repository;

import com.mcgb.varbifikrimbackend.entity.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, ObjectId> {
    //List<Question> findAllBySurvey_Id(ObjectId surveyId);
}
