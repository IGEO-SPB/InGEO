package org.geoproject.ingeo.services.common;

import org.geoproject.ingeo.dto.SurveyPointDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.MainViewService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SurveyPointsService extends MainViewService<SurveyPoint, SurveyPointDto> {
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
    void delete(SurveyPointDto object);

    @Override
    default void deleteByDto(SurveyPointDto dto) {

    }

    @Override
    List<SurveyPointDto> getDtos(List<SurveyPoint> objects);

    @Override
    void updateFromDtos(List<SurveyPoint> objects, List<SurveyPointDto> dtos);

    @Override
    default void updateFromDtos(List<SurveyPointDto> dtos) {

    }

    @Override
    List<SurveyPointDto> getDtosByProject(Project project);

    @Override
    default List<SurveyPointDto> getDtosBySurveyPointId(Long surveyPointId) {
        return null;
    }

    @Override
    SurveyPointDto cloneDto(SurveyPointDto egeDto);

    @Override
    default void enrichEntity(Long updatedEntityId, Long sourceEntityId) {

    }

    SurveyPoint getByPointNumber(String pointNumber, Project project);

    List<SurveyPoint> getAllByProject(Project currentProject);
}
