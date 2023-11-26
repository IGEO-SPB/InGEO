package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DensityDTOFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    PYCNOMETER_WEIGHT_WITH_DRY_SOIL_FIRST_MEASUREMENT("pycnometerWeightWithDrySoilFirstMeasurement"),
    EMPTY_PYCNOMETER_WEIGHT_FIRST_MEASUREMENT("emptyPycnometerWeightFirstMeasurement"),
    PYCNOMETER_WEIGHT_WITH_WATER_FIRST_MEASUREMENT("pycnometerWeightWithWaterFirstMeasurement"),
    PYCNOMETER_WEIGHT_WITH_SOIL_AND_WATER_FIRST_MEASUREMENT("pycnometerWeightWithSoilAndWaterFirstMeasurement"),
    DRY_SOIL_WEIGHT_FIRST_MEASUREMENT("drySoilWeightFirstMeasurement"),
    SOIL_VOLUME_FIRST_MEASUREMENT("soilVolumeFirstMeasurement"),
    SOIL_DENSITY_FIRST_MEASUREMENT("soilDensityFirstMeasurement"),
    PYCNOMETER_WEIGHT_WITH_DRY_SOIL_SECOND_MEASUREMENT("pycnometerWeightWithDrySoilSecondMeasurement"),
    EMPTY_PYCNOMETER_WEIGHT_SECOND_MEASUREMENT("emptyPycnometerWeightSecondMeasurement"),
    PYCNOMETER_WEIGHT_WITH_WATER_SECOND_MEASUREMENT("pycnometerWeightWithWaterSecondMeasurement"),
    PYCNOMETER_WEIGHT_WITH_SOIL_AND_WATER_SECOND_MEASUREMENT("pycnometerWeightWithSoilAndWaterSecondMeasurement"),
    DRY_SOIL_WEIGHT_SECOND_MEASUREMENT("drySoilWeightSecondMeasurement"),
    SOIL_VOLUME_SECOND_MEASUREMENT("soilVolumeSecondMeasurement"),
    SOIL_DENSITY_SECOND_MEASUREMENT("soilDensitySecondMeasurement"),
    AVERAGE_DENSITY("averageDensity");

    private final String name;

    public static DensityDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(DensityDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}