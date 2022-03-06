package com.mcgb.varbifikrimbackend.entity;

import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

import java.util.List;

@Data
@TypeAlias(value = "multipleChoiceQuestion")
public class MultipleChoiceQuestion extends Question {
    private List<String> choices;
    private Integer scale;
}
