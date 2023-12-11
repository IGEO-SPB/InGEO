package org.geoproject.ingeo.services.common;

import org.geoproject.ingeo.dto.*;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.MainViewService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SampleService extends MainViewService<Sample, SampleDto> {

    @Override
    List<Sample> getAll();

    @Override
    Sample getById(Long id);

//    @Override
//    void create(SampleDto object);

    @Override
    void create(List<Sample> objectList);

    @Override
    void update(Sample object);

    @Override
    void delete(List<Sample> object);

    @Override
    List<Sample> getByProject(Project project);

    @Override
    List<Sample> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    Sample getByLaborNumber(String laborNumber);

    Sample getBySurveyPointAndLaborNumber(String laborNumber);

    void setFirstSampleForView();

    @Override
    void delete(SampleDto object);

    @Override
    default void deleteByDto(SampleDto dto) {

    }

    @Override
    List<SampleDto> getDtos(List<Sample> objects);

    @Override
    void updateFromDtos(List<Sample> objects, List<SampleDto> dtos);

    @Override
    default void updateFromDtos(List<SampleDto> dtos) {

    }

    @Override
    default List<SampleDto> getDtosByProject(Project project) {
        return null;
    }
}
