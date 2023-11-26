package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum WaterExtractFullDtoFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    DEPTH_FROM("depthFrom"),
    DEPTH_TO("depthTo"),
    SAMPLING_DATE("samplingDate"),
    IS_ARCHIVE("isArchive"),
    IS_BLOCKED("isBlocked"),
    HCO3_1("HCO3_1"),
    HCO3_2("HCO3_2"),
    CO3_1("CO3_1"),
    CO3_2("CO3_2"),
    CL_1("Cl_1"),
    CL_2("Cl_2"),
    SO4_1("SO4_1"),
    SO4_2("SO4_2"),
    CA_1("Ca_1"),
    CA_2("Ca_2"),
    MG_1("Mg_1"),
    MG_2("Mg_2"),
    DRY_1("Dry_1"),
    DRY_2("Dry_2"),
    O2_1("O2_1"),
    O2_2("O2_2"),
    PH("pH");

    private final String name;

    public static WaterExtractFullDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WaterExtractFullDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
