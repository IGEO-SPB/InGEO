package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ConstructionMeshResultDTOFieldsEnum {
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
    X_25_2("X_25_2"),
    X_2_125("X_2_125"),
    X_125_1("X_125_1"),
    X_1_063("X_1_063"),
    X_063_050("X_063_050"),
    X_050_0315("X_050_0315"),
    X_0315_025("X_0315_025"),
    X_025_016("X_025_016"),
    X_016_01("X_016_01"),
    X_01_005("X_01_005"),
    X_LESS_005("X_less_005"),
    CORRECTION("correction"),
    SOIL_KIND("soilKind"),
    UNIFORMITY_COEFFICIENT("uniformityCoefficient"),
    UNIFORMITY_DEGREE("uniformityDegree"),
    FINENESS_MODULUS("finenessModulus"),
    SAND_GROUP_BY_FINENESS_MODULUS("sandGroupByFinenessModulus"),
    LENSES_AND_SEAMS("lensesAndSeams");

    private final String name;

    public static ConstructionMeshResultDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(ConstructionMeshResultDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
