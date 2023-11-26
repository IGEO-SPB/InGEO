package com.geoproject.igeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WaterExtractPartialResultDtoFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    DEPTH_FROM("depthFrom"),
    DEPTH_TO("depthTo"),
    SAMPLING_DATE("samplingDate"),
    IS_ARCHIVE("isArchive"),
    IS_BLOCKED("isBlocked"),
    PH("pH"),
    CL("Cl"),
    NO3_TXT("NO3Txt"),
    FE_TXT("FeTxt"),
    GUM("gum"),
    SO4("SO4"),
    CL_V("Cl_v"),
    SO4_V("SO4_v"),
    ClSO4("ClSO4");

    private final String name;

    public static WaterExtractPartialResultDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WaterExtractPartialResultDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}