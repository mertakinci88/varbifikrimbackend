package com.mcgb.varbifikrimbackend.service;

import com.mcgb.varbifikrimbackend.entity.Survey;
import com.mcgb.varbifikrimbackend.repository.SurveyRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    public List<Survey> getAllSurvey(ObjectId userId) {
        return surveyRepository.findAllByUserId(userId);
    }
}
