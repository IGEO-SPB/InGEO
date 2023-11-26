package org.geoproject.ingeo.services.allProjects.impl;

import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.ProjectMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.geoproject.ingeo.repositories.EmployeesRepository;
import org.geoproject.ingeo.repositories.ProjectsRepository;
import org.geoproject.ingeo.services.classificators.ConstructionTypeService;
import org.geoproject.ingeo.services.allProjects.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_ARCHIVE;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final EmployeesRepository employeesRepository;

    private final ProjectMapper projectMapper;

    private final ConstructionTypeService constructionTypeService;

    @Override
    public List<Project> getAll() {
        Sort sort = Sort.by("id");

        return projectsRepository.findAll(sort);
    }

    public Project getById(Long id) {
        return projectsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Проект не найден"));
    }

    @Override
    public Project getBySample(Sample __) {
        return null;
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
        return null;
    }

    @Override
    public List<Project> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        return null;
    }

    @Override
    public List<ProjectDto> getDtos(List<Project> entities) {
        System.out.println("init get dtos");
        return projectMapper.projectToProjectDto(entities);
    }

    @Override
    @Transactional
    public void updateFromDtos(List<Project> objects, List<ProjectDto> dtos) {
        projectMapper.updateProject(objects, dtos);

        var testObject = projectsRepository.findById(1L);
        System.out.println("testObject");
        System.out.println(testObject.get().getId());
        System.out.println(testObject.get().getCreatedBy().getId());
        System.out.println(testObject.get().getCreatedBy().getName());
        System.out.println(testObject.get().getExecutor().getId());
        System.out.println(testObject.get().getExecutor().getName());
        System.out.println(testObject.get().getApprover().getId());
        System.out.println(testObject.get().getApprover().getName());

        System.out.println("Before saving:");
        System.out.println(objects.get(0).getId());
        System.out.println(objects.get(0).getCreatedBy().getId());
        System.out.println(objects.get(0).getCreatedBy().getName());
        System.out.println(objects.get(0).getExecutor().getId());
        System.out.println(objects.get(0).getExecutor().getName());
        System.out.println(objects.get(0).getApprover().getId());
        System.out.println(objects.get(0).getApprover().getName());

//    (e -> {
//            System.out.println(e.getId());
//            System.out.println(e.getCreatedBy().getId());
//            System.out.println(e.getCreatedBy().getName());
//            System.out.println(e.getExecutor().getId());
//            System.out.println(e.getExecutor().getName());
//            System.out.println(e.getApprover().getId());
//            System.out.println(e.getApprover().getName());
//        });

//        employeesRepository.save(objects.get(0).getCreatedBy());
//        employeesRepository.save(objects.get(0).getExecutor());
//        employeesRepository.save(objects.get(0).getApprover());

        projectsRepository.saveAllAndFlush(objects);
    }

    @Override
    public Project getByContractNumber(String contractNumber) {
        return projectsRepository.findByContractNumber(contractNumber);
    }
}
