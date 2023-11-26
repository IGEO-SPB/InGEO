package org.geoproject.ingeo.services.objectChoice;

import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.MainViewService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProjectsService extends MainViewService<Project, ProjectDto> {

    @Override
    List<Project> getAll();

    @Override
    Project getById(Long id);

    @Override
    Project getBySample(Sample sample);

    @Override
    void create(ProjectDto dto);

    @Override
    void create(List<Project> objectList);

    @Override
    void update(Project object);

    @Override
    void delete(List<Project> object);

    @Override
    void delete(ProjectDto object);

    @Override
    List<Project> getByProject(Project project);

    @Override
    List<Project> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber);

    @Override
    List<ProjectDto> getDtos(List<Project> objects);

    @Override
    void updateFromDtos(List<Project> objects, List<ProjectDto> dtos);

    Project getByContractNumber(String contractNumber);
}
