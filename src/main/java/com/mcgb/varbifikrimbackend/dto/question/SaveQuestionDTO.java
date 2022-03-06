package com.mcgb.varbifikrimbackend.dto.question;

import com.mcgb.varbifikrimbackend.enums.QuestionTypeEnum;
import lombok.Data;

@Data
public class SaveQuestionDTO {
    private Integer order;
    private String title;
    private String subTitle;
    private QuestionTypeEnum questionType;
}
