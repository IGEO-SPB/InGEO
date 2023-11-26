package org.geoproject.ingeo.dto.mainViewsDtos;

import org.geoproject.ingeo.enums.dtoenums.ProjectDTOFieldsEnum;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Employee;
import org.geoproject.ingeo.models.SurveyPoint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectDto {

    private Long id;
    private String contractNumber;
    private String projectName;
    private String region;
    private String city;
    private String district;
    private String town;
    private String street;
    private String constructionType;
    private String reportName;
    private String archiveNumber;
    private Float absoluteMediumWinterTemperature;
    private LocalDate assignmentDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectingStage;
    private Float coordinateX;
    private Float coordinateY;
    private Employee createdBy;
    private Employee executor;
    private Employee approver;
    private List<SurveyPoint> surveyPoints;
    private List<Ege> egeList;
    private Boolean isArchive;

    public void setFieldValue(ProjectDTOFieldsEnum field, Object value) {
        switch (field) {
            case CREATED_BY -> createdBy = (Employee) value;
            case CONTRACT_NUMBER -> contractNumber = (String) value;
            case PROJECT_NAME -> projectName = (String) value;
            case REGION -> region = (String) value;
            case CITY -> city = (String) value;
            case DISTRICT -> district = (String) value;
            case TOWN -> town = (String) value;
            case STREET -> street = (String) value;
            case CONSTRUCTION_TYPE -> constructionType = (String) value;
            case REPORT_NAME -> reportName = (String) value;
            case ARCHIVE_NUMBER -> archiveNumber = (String) value;
            case ABSOLUTE_MEDIUM_WINTER_TEMPERATURE -> absoluteMediumWinterTemperature = Float.parseFloat((String) value);
            case EXECUTOR -> executor = (Employee) value;
            case APPROVER -> approver = (Employee) value;
            case ASSIGNMENT_DATE -> assignmentDate = (LocalDate) value;
            case START_DATE -> startDate = (LocalDate) value;
            case END_DATE -> endDate = (LocalDate) value;
            case PROJECTING_STAGE -> projectingStage = (String) value;
            case COORDINATE_X -> coordinateX = Float.parseFloat((String) value);
            case COORDINATE_Y -> coordinateY = Float.parseFloat((String) value);
            case IS_ARCHIVE -> isArchive = (Boolean) value;
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
}