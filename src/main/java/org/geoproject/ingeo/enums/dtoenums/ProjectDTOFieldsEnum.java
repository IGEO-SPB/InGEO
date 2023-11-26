package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ProjectDTOFieldsEnum {
    CREATED_BY("createdBy"),
    CONTRACT_NUMBER("contractNumber"),
    PROJECT_NAME("projectName"),
    REGION("region"),
    CITY("city"),
    DISTRICT("district"),
    TOWN("town"),
    STREET("street"),
    CONSTRUCTION_TYPE("constructionType"),
    REPORT_NAME("reportName"),
    ARCHIVE_NUMBER("archiveNumber"),
    ABSOLUTE_MEDIUM_WINTER_TEMPERATURE("absoluteMediumWinterTemperature"),
    EXECUTOR("executor"),
    APPROVER("approver"),
    ASSIGNMENT_DATE("assignmentDate"),
    START_DATE("startDate"),
    END_DATE("endDate"),
    PROJECTING_STAGE("projectingStage"),
    COORDINATE_X("coordinateX"),
    COORDINATE_Y("coordinateY"),
    POINTS("points"),
    EGE_LIST("egeList"),
    IS_ARCHIVE("isArchive");

    private final String name;

    public static ProjectDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(ProjectDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}

