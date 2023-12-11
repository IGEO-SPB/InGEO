package org.geoproject.ingeo.services.common;

import org.geoproject.ingeo.dto.SurveyPointDTO;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.MainViewService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SurveyPointsService extends MainViewService<SurveyPoint, SurveyPointDTO> {
    @Override
    List<SurveyPoint> getAll();

    @Override
    SurveyPoint getById(Long id);

//    @Override
//    void create(SurveyPointDTO object);

    @Override
    void create(List<SurveyPoint> objectList);

    @Override
    void update(SurveyPoint object);

    @Override
    void delete(List<SurveyPoint> object);

    @Override
    List<SurveyPoint> getByProject(Project project);

    @Override
    List<SurveyPoint> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    SurveyPoint findByPointNumberAndProject(String number, Project project);

    @Override
    void delete(SurveyPointDTO object);

    @Override
    default void deleteByDto(SurveyPointDTO dto) {

    }

    @Override
    List<SurveyPointDTO> getDtos(List<SurveyPoint> objects);

    @Override
    void updateFromDtos(List<SurveyPoint> objects, List<SurveyPointDTO> dtos);

    @Override
    default void updateFromDtos(List<SurveyPointDTO> dtos) {

    }

    @Override
    default List<SurveyPointDTO> getDtosByProject(Project project) {
        return null;
    }

    SurveyPoint getByPointNumber(String pointNumber, Project project);

    List<SurveyPoint> getAllByProject(Project currentProject);
}
