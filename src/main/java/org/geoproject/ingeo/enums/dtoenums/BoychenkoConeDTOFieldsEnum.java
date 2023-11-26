package com.geoproject.igeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
//@RequiredArgsConstructor
public enum BoychenkoConeDTOFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    UNDISTURBED_STR_IMMERSION_DEPTH_FIRST_MEASUR("undisturbedStrImmersionDepthFirstMeasur"),
    UNDISTURBED_STR_IMMERSION_DEPTH_SECOND_MEASUR("undisturbedStrImmersionDepthSecondMeasur"),
    UNDISTURBED_STR_IMMERSION_DEPTH_THIRD_MEASUR("undisturbedStrImmersionDepthThirdMeasur"),
    BROKEN_STR_IMMERSION_DEPTH_FIRST_MEASUR("brokenStrImmersionDepthFirstMeasur"),
    BROKEN_STR_IMMERSION_DEPTH_SECOND_MEASUR("brokenStrImmersionDepthSecondMeasur"),
    BROKEN_STR_IMMERSION_DEPTH_THIRD_MEASUR("brokenStrImmersionDepthThirdMeasur"),
    UNDISTURBED_STR_IMMERSION_DEPTH_AVERAGE("undisturbedStrImmersionDepthAverage"),
    BROKEN_STR_IMMERSION_DEPTH_AVERAGE("brokenStrImmersionDepthAverage");

    private final String name;

    BoychenkoConeDTOFieldsEnum(String name) {
        this.name = name;
    }

    public static BoychenkoConeDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(BoychenkoConeDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
