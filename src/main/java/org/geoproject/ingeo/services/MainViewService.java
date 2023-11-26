package org.geoproject.ingeo.services.mainViews;

import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MainViewService<T, Y> {
    List<T> getAll();

    T getById(Long id);

    T getBySample(Sample sample);

    void create(Y dto);

    void create(List<T> objectList);

    void update(T object);

    void delete(List<T> object);

    void delete(Y object);

    List<T> getByProject(Project project);

    List<T> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    List<Y> getDtos(List<T> objects);

    void updateFromDtos(List<T> objects, List<Y> dtos);
}
