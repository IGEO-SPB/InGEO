package org.geoproject.ingeo.services.objectChoice.impl;

import org.geoproject.ingeo.dto.mainViewsDtos.EditProjectDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.ProjectMapper;
import org.geoproject.ingeo.models.Employee;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.geoproject.ingeo.repositories.ProjectsRepository;
import org.geoproject.ingeo.services.ModalWindowService;
import org.geoproject.ingeo.services.admin.EmployeesService;
import org.geoproject.ingeo.services.classificators.ConstructionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static org.geoproject.ingeo.constants.JavaFXConstants.EMPLOYEE_FIELD_PATTERN;
import static org.geoproject.ingeo.constants.JavaFXConstants.SECOND_INDEX;

@Service
@RequiredArgsConstructor
public class EditProjectServiceImpl implements ModalWindowService<Project, EditProjectDto> {

    private final ProjectMapper projectMapper;

    private final ConstructionTypeService constructionTypeService;
    private final EmployeesService employeesService;

    private final ProjectsRepository projectsRepository;

    @Override
    public void create(EditProjectDto dto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));

    }

    @Override
    public void update(EditProjectDto dto) {
        Project updatedProject = getById(dto.getId());

        projectMapper.updateProjectFromEditProjectDto(updatedProject, dto);

        ConstructionType constructionType = constructionTypeService.getByType(dto.getConstructionType());
        updatedProject.setConstructionType(constructionType);

        updatedProject.setAssignmentDate(dto.getAssignmentDate());
        updatedProject.setStartDate(dto.getStartDate());
        updatedProject.setEndDate(dto.getEndDate());

        Employee createdBy = employeesService.getById(Long.valueOf(dto.getCreatedBy().split(EMPLOYEE_FIELD_PATTERN)[SECOND_INDEX]));
        updatedProject.setCreatedBy(createdBy);

        Employee executor = employeesService.getById(Long.valueOf(dto.getExecutor().split(EMPLOYEE_FIELD_PATTERN)[SECOND_INDEX]));
        updatedProject.setExecutor(executor);

        Employee approver = employeesService.getById(Long.valueOf(dto.getApprover().split(EMPLOYEE_FIELD_PATTERN)[SECOND_INDEX]));
        updatedProject.setApprover(approver);

        projectsRepository.save(updatedProject);
    }

    @Override
    public EditProjectDto getDto(Project project) {
        EditProjectDto editProjectDto = projectMapper.projectToEditProjectDto(project);

        editProjectDto.setCreatedBy(project.getCreatedBy().getName() + EMPLOYEE_FIELD_PATTERN +
                project.getCreatedBy().getId());

        editProjectDto.setExecutor(project.getExecutor().getName() + EMPLOYEE_FIELD_PATTERN +
                project.getExecutor().getId());

        editProjectDto.setApprover(project.getApprover().getName() + EMPLOYEE_FIELD_PATTERN +
                project.getApprover().getId());

        return editProjectDto;
    }

    private Project getById(Long projectId) {
        return projectsRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Project")));
    }
}
