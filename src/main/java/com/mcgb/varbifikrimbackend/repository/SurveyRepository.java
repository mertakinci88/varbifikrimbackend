package com.mcgb.varbifikrimbackend.repository;

import com.mcgb.varbifikrimbackend.entity.Question;
import com.mcgb.varbifikrimbackend.entity.Survey;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SurveyRepository extends MongoRepository<Survey, ObjectId> {
    List<Survey> findAllByUserId(ObjectId userId);
    @Query("{id: ?0}")
    List<Question> findAllQuestionsBySurveyId(ObjectId surveyId);
}
