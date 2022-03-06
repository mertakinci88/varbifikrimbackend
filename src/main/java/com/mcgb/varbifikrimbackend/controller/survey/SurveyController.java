package com.mcgb.varbifikrimbackend.controller.survey;

import com.mcgb.varbifikrimbackend.converter.SurveyConverter;
import com.mcgb.varbifikrimbackend.dto.survey.ListSurveysDTO;
import com.mcgb.varbifikrimbackend.entity.Survey;
import com.mcgb.varbifikrimbackend.security.JWTTokenUtil;
import com.mcgb.varbifikrimbackend.service.SurveyService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;
    @Autowired
    JWTTokenUtil jwtTokenUtil;
    @Autowired
    SurveyConverter surveyConverter;

    @GetMapping("/all")
    public ResponseEntity<?> getAllSurveys(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String userId = (String) jwtTokenUtil.getUserClaimFromToken(authHeader, "userId");
        List<Survey> surveyList = surveyService.getAllSurvey(new ObjectId(userId));
        List<ListSurveysDTO> responseList = surveyList.stream().map(survey ->
            surveyConverter.convertToListSurveyDto(survey)
        ).collect(Collectors.toList());
         return ResponseEntity.ok(responseList);
    }

    @PutMapping("/update")
    public ResponseEntity updateSurvey() {
        return null;
    }

    @PostMapping("/addQuestion")
    public ResponseEntity addQuestionToSurvey() {
        return null;
    }

    @PostMapping("/deleteQuestion")
    public ResponseEntity deleteQuestionFromSurvey() {
        return null;
    }

}
