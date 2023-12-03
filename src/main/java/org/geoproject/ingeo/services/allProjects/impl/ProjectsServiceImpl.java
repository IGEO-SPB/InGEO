package org.geoproject.ingeo.services.allProjects.impl;

import lombok.RequiredArgsConstructor;
import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.ProjectMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.geoproject.ingeo.repositories.ProjectsRepository;
import org.geoproject.ingeo.services.allProjects.ProjectsService;
import org.geoproject.ingeo.services.classificators.ConstructionTypeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_ARCHIVE;
import static org.geoproject.ingeo.constants.ServiceConstants.ID_FIELD;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;

    private final ProjectMapper projectMapper;

    private final ConstructionTypeService constructionTypeService;

    @Override
    public List<Project> getAll() {
        Sort sort = Sort.by(ID_FIELD);

        return projectsRepository.findAllByIsArchiveFalse(sort);
    }

    public Project getById(Long id) {
        return projectsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Проект не найден"));
    }

    @Override
    public Project getBySample(Sample __) {
        throw new NotImplemented("getBySample method in ProjectServise not implemented");
    }

    @Override
    @Transactional
    public void create(ProjectDto dto) {
        Project newProject = projectMapper.projectDtoToProject(dto);

        ConstructionType constructionType = constructionTypeService.getByType(dto.getConstructionType());
        newProject.setConstructionType(constructionType);

        projectsRepository.save(newProject);
    }

    @Override
    @Transactional
    public void create(List<Project> object) {
        throw new NotImplemented("create method in ProjectServise not implemented");

    }

    @Override
    @Transactional
    public void update(Project object) {
        throw new NotImplemented("update method in ProjectServise not implemented");

    }

    @Override
    @Transactional
    public void delete(List<Project> object) {
        throw new NotImplemented("delete method in ProjectServise not implemented");

    }

    @Override
    @Transactional
    public void delete(ProjectDto dto) {
        Project project = getById(dto.getId());

        project.setIsArchive(ENTITY_IS_ARCHIVE);

        projectsRepository.save(project);
    }

    @Override
    public List<Project> getByProject(Project project) {
        throw new NotImplemented("getByProject method in ProjectServise not implemented");
    }

    @Override
    public List<Project> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        throw new NotImplemented("getBySurveyPoint method in ProjectServise not implemented");
    }

    @Override
    public List<ProjectDto> getDtos(List<Project> entities) {
        return projectMapper.projectToProjectDto(entities);
    }

    @Override
    @Transactional
    public void updateFromDtos(List<Project> objects, List<ProjectDto> dtos) {
        projectMapper.updateProject(objects, dtos);

        projectsRepository.saveAllAndFlush(objects);
    }

    @Override
    public Project getByContractNumber(String contractNumber) {
        return projectsRepository.findByContractNumber(contractNumber);
    }
}
