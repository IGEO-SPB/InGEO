package com.geoproject.igeo.services.classificators;

import com.geoproject.igeo.models.SurveyPointsType;

import java.util.List;

public interface SurveyPointsTypesService {

    SurveyPointsType findOne(int id);

    List<SurveyPointsType> findAll();

    SurveyPointsType findBySurveyTypeShortName(String surveyTypeShortName);
}
