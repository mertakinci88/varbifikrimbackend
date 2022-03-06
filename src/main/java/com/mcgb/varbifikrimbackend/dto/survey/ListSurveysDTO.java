package com.mcgb.varbifikrimbackend.dto.survey;

import com.mcgb.varbifikrimbackend.enums.SurveyTypeEnum;
import lombok.Data;

@Data
public class ListSurveysDTO {
    private String id;
    private SurveyTypeEnum surveyType;
    private String startMessage;
    private String finishMessage;
    private String recordTime;
    private String updateTime;
}
