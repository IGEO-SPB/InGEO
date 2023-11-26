package com.geoproject.igeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WaterExtractPartialDtoFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    DEPTH_FROM("depthFrom"),
    DEPTH_TO("depthTo"),
    SAMPLING_DATE("samplingDate"),
    IS_ARCHIVE("isArchive"),
    IS_BLOCKED("isBlocked"),
    PH("pH"),
    CL_1("Cl_1"),
    CL_2("Cl_2"),
    NO3("NO3"),
    FE("Fe"),
    GUM_1("gum_1"),
    GUM_2("gum_2"),
    SO4_1("SO4_1"),
    SO4_2("SO4_2");

    private final String name;

    public static WaterExtractPartialDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WaterExtractPartialDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}