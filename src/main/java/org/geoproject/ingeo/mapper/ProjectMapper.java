package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.mainViewsDtos.EditProjectDto;
import org.geoproject.ingeo.dto.mainViewsDtos.ProjectDto;
import org.geoproject.ingeo.models.Employee;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.classificators.ConstructionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.geoproject.ingeo.constants.JavaFXConstants.EMPLOYEE_FIELD_PATTERN;

@Mapper(config = MapStructConfiguration.class,
imports = {Employee.class, ConstructionType.class, LocalDate.class})
public interface ProjectMapper {

    @Mapping(target = "mapPoint", ignore = true)
    @Mapping(target = "constructionType", ignore = true)
    @Mapping(target = "surveyPoints", ignore = true)
    @Mapping(target = "isArchive", defaultValue = "false")
    Project projectDtoToProject(ProjectDto dto);

    ProjectDto projectToProjectDto(Project project);

    EditProjectDto projectToEditProjectDto(Project project);

    default String map(Employee employee) {
        return employee.getName() + EMPLOYEE_FIELD_PATTERN + employee.getId();
    }

    default String constructionTypeToTypeName(ConstructionType constructionType) {
        return constructionType.getType();
    }

    List<ProjectDto> projectToProjectDto(List<Project> project);


    @Mapping(target = "constructionType", ignore = true)
    @Mapping(target = "assignmentDate", defaultExpression = "java(LocalDate.now())")
    @Mapping(target = "startDate", defaultExpression = "java(LocalDate.now())")
    @Mapping(target = "endDate", defaultExpression = "java(LocalDate.now())")
    @Mapping(target = "mapPoint", ignore = true)
    @Mapping(target = "surveyPoints", ignore = true)
    void updateProject(@MappingTarget Project object, ProjectDto dto);

    default void updateProject(List<Project> objects, List<ProjectDto> dtos) {
        for (var object : objects) {
            for (var sourceDTO : dtos) {
                if (Objects.equals(object.getId(), sourceDTO.getId())) {
                    updateProject(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "constructionType", ignore = true)
    @Mapping(target = "assignmentDate", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "mapPoint", ignore = true)
    @Mapping(target = "surveyPoints", ignore = true)
    @Mapping(target = "egeList", ignore = true)
    @Mapping(target = "isArchive", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "executor", ignore = true)
    @Mapping(target = "approver", ignore = true)
    void updateProjectFromEditProjectDto(@MappingTarget Project object, EditProjectDto dto);
}
