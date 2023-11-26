package org.geoproject.ingeo.services.allProjects.impl;

import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.ProjectMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.geoproject.ingeo.repositories.ProjectsRepository;
import org.geoproject.ingeo.services.ModalWindowService;
import org.geoproject.ingeo.services.classificators.ConstructionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProjectServiceImpl implements ModalWindowService<Project, ProjectDto> {

    private final ProjectsRepository projectsRepository;

    private final ProjectMapper projectMapper;
    private final ConstructionTypeService constructionTypeService;

    @Override
    public void create(ProjectDto dto) {
        Project newProject = projectMapper.projectDtoToProject(dto);

        ConstructionType constructionType = constructionTypeService.getByType(dto.getConstructionType());
        newProject.setConstructionType(constructionType);

        projectsRepository.save(newProject);
    }

    @Override
    public void update(ProjectDto dto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("update"));
    }

    @Override
    public ProjectDto getDto(Project entity) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDto"));

    }
}
