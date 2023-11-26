package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ConstructionMeshAreometryResultDTOFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    DEPTH_MIN("depthMin"),
    DEPTH_MAX("depthMax"),
    X_MORE_70("X_more_70"),
    X_70_40("X_70_40"),
    X_40_20("X_40_20"),
    X_20_10("X_20_10"),
    X_10_5("X_10_5"),
    X_5_25("X_5_25"),
    X_25_125("X_25_125"),
    X_125_063("X_125_063"),
    X_063_0315("X_063_0315"),
    X_0315_016("X_0315_016"),
    X_016_01("X_016_01"),
    X_01_005("X_01_005"),
    X_LESS_005("X_less_005"),
    CORRECTION("correction"),
    UNIFORMITY_COEFFICIENT("uniformityCoefficient"),
    UNIFORMITY_DEGREE("uniformityDegree"),
    FINENESS_MODULUS("finenessModulus"),
    SAND_GROUP_BX_FINENESS_MODULUS("sandGroupByFinenessModulus");

    private final String name;

    public static ConstructionMeshAreometryResultDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(ConstructionMeshAreometryResultDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
