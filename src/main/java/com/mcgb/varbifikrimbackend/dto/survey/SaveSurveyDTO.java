package com.mcgb.varbifikrimbackend.dto.survey;

import com.mcgb.varbifikrimbackend.enums.SurveyTypeEnum;
import lombok.Data;

@Data
public class SaveSurveyDTO {
    private SurveyTypeEnum surveyType;
    private String startMessage;
    private String finishMessage;
}