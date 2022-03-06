package com.mcgb.varbifikrimbackend.entity;

import com.mcgb.varbifikrimbackend.enums.QuestionTypeEnum;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Question {
    @Id
    private ObjectId id;
    private Integer order;
    private String title;
    private String subTitle;
    private QuestionTypeEnum questionType;
}
