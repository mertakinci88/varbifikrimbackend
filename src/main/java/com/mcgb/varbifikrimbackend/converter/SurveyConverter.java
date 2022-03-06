package com.mcgb.varbifikrimbackend.converter;

import com.mcgb.varbifikrimbackend.dto.survey.ListSurveysDTO;
import com.mcgb.varbifikrimbackend.entity.Survey;
import com.mcgb.varbifikrimbackend.util.helper.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Optional;

@Component
public class SurveyConverter {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DateUtil dateUtil;

    public ListSurveysDTO convertToListSurveyDto(Survey survey) {
        ListSurveysDTO listSurveysDTO = modelMapper.map(survey, ListSurveysDTO.class);
        listSurveysDTO.setRecordTime(dateUtil.convertDateToDDMMYYYYHHMMSS(survey.getRecordTime()));
        listSurveysDTO.setUpdateTime(dateUtil.convertDateToDDMMYYYYHHMMSS(survey.getUpdateTime()));
        return listSurveysDTO;
    }
}
