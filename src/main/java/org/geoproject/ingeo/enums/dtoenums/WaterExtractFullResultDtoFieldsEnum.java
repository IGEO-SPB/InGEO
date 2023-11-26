package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WaterExtractFullResultDtoFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    DEPTH_FROM("depthFrom"),
    DEPTH_TO("depthTo"),
    SAMPLING_DATE("samplingDate"),
    IS_ARCHIVE("isArchive"),
    IS_BLOCKED("isBlocked"),
    HCO3_V("HCO3_v"),
    HCO3_EQ("HCO3_eq"),
    HCO3_PROC("HCO3_proc"),
    CO3_V("CO3_v"),
    CO3_EQ("CO3_eq"),
    CO3_PROC("CO3_proc"),
    CL_V("Cl_v"),
    CL_EQ("Cl_eq"),
    CL_PROC("Cl_proc"),
    SO4_V("SO4_v"),
    SO4_EQ("SO4_eq"),
    SO4_PROC("SO4_proc"),
    AN_SUM_EQ("anSumEq"),
    AN_SUM_PROC("anSumProc"),
    CA_V("Ca_v"),
    CA_EQ("Ca_eq"),
    CA_PROC("Ca_proc"),
    MG_V("Mg_v"),
    MG_EQ("Mg_eq"),
    MG_PROC("Mg_proc"),
    NA_V("Na_v"),
    NA_EQ("Na_eq"),
    NA_PROC("Na_proc"),
    KAT_SUM_EQ("katSumEq"),
    KAT_SUM_PROC("katSumProc"),
    ION_SUM("ionSum"),
    DRY("Dry"),
    O2("O2"),
    PH("pH"),
    GUM("gum");

    private final String name;

    public static WaterExtractFullResultDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WaterExtractFullResultDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
