package org.geoproject.ingeo.services.classificators;

import org.geoproject.ingeo.models.classificators.SurveyPointsType;

import java.util.List;

public interface SurveyPointsTypesService {

    SurveyPointsType findOne(int id);

    List<SurveyPointsType> findAll();

    SurveyPointsType findBySurveyTypeShortName(String surveyTypeShortName);
}
