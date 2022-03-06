package com.mcgb.varbifikrimbackend.entity;

import com.mcgb.varbifikrimbackend.enums.SurveyTypeEnum;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "surveys")
@TypeAlias(value = "survey")
public class Survey {
    @Id
    private ObjectId id;
    @Indexed
    private ObjectId userId;
    private SurveyTypeEnum surveyType;
    private String startMessage;
    private String finishMessage;
    private Date recordTime;
    private Date updateTime;
    private List<Question> questions;
}
