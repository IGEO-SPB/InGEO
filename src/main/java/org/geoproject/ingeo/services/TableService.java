package org.geoproject.ingeo.services;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;

import java.util.List;

public interface TableService<T, Y> {

    List getDTOs(SurveyPoint surveyPoint);

    Y getDto(T entity);

    void updateFromDtos(List<Y> dtos);

    void updateFromDTO(Sample sample, Y sourceDto);

    List<T> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    void create(List<Y> dtos);

    void delete(Y dto);

    Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project);

    void calculate(List<Y> dtos);
}
