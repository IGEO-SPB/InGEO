package com.geoproject.igeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum GranCompositionDTOFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    DEPTH_MIN("depthMin"),
    DEPTH_MAX("depthMax"),
    INCLUDED_MORE_2("included_more_2"),
    X_MORE_10("X_more_10"),
    X_10_5_OLD_10_2("X_10_5_old_10_2"),
    X_5_2("X_5_2"),
    X_2_1("X_2_1"),
    X_1_05("X_1_05"),
    X_05_025("X_05_025"),
    X_025_01("X_025_01"),
    X_01_005("X_01_005"),
    X_005_001("X_005_001"),
    X_001_0002_OLD_001_0005("X_001_0002_old_001_0005"),
    X_0005_0002("X_0005_0002"),
    X_LESS_0002("X_less_0002"),
    SUM_2_005("sum_2_005"),
    SUM_005_0002_OLD_005_0005("sum_005_0002_old_005_0005"),
    SUM_LESS_0002_OLD_LESS_0005("sum_less_0002_old_less_0005"),
    SOIL_KIND("soilKind"),
    UNIFORMITY_COEFFICIENT("uniformityCoefficient"),
    UNIFORMITY_DEGREE("uniformityDegree"),
    HCL("HCl"),
    DISPERSITY_INDEX("dispersityIndex"),
    HEAVING_DEGREE("heavingDegree"),
    IS_SAND("isSand");

    private final String name;

    public static GranCompositionDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(GranCompositionDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}


