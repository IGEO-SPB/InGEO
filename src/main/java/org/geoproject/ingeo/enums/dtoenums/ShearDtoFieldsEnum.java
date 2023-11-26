package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ShearDtoFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    IS_ARCHIVE("isArchive"),
    DEPTH("depth"),
    KD("kd"),
    SHEAR_POINT_NUMBER("shearPointNumber"),
    VERTICAL_LOADING("verticalLoading"),
    SHEAR_STRENGTH("shearStrength"),
    IS_EXCLUDED("isExcluded"),
    DENSITY_BEFORE("densityBefore"),
    WATER_CONTENT_BEFORE("waterContentBefore"),
    WATER_CONTENT_AFTER("waterContentAfter"),
    SOIL_DESCRIPTION("soilDescription"),

    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_FIRST_MEASUREMENT("shearNaturalWaterContentWeighingBottleFirstMeasurement"),
    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_SECOND_MEASUREMENT("shearNaturalWaterContentWeighingBottleSecondMeasurement"),
    SHEAR_RING_DENSITY_FIRST_MEASUREMENT("shearRingDensityFirstMeasurement");

//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT("shearNaturalWaterContentWeighingBottleNumberFirstMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT("shearNaturalWaterContentWeighingBottleNumberSecondMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT("shearNaturalWaterContentEmptyWeighingBottleMassFirstMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT("shearNaturalWaterContentEmptyWeighingBottleMassSecondMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT("shearNaturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT("shearNaturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT("shearNaturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT("shearNaturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_FIRST_MEASUREMENT("shearNaturalWaterContentWeighingBottleFirstMeasurement"),
//    SHEAR_NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_SECOND_MEASUREMENT("shearNaturalWaterContentWeighingBottleSecondMeasurement"),
//    NATURAL_SHEAR_AVERAGE_WATER_CONTENT_FIRST_MEASUREMENT("naturalShearAverageWaterContentFirstMeasurement"),
//    SHEAR_RING_NUMBER_FIRST_MEASUREMENT("shearRingNumberFirstMeasurement"),
//    SHEAR_RING_NUMBER_SECOND_MEASUREMENT("shearRingNumberSecondMeasurement"),
//    SHEAR_EMPTY_RING_MASS_FIRST_MEASUREMENT("shearEmptyRingMassFirstMeasurement"),
//    SHEAR_EMPTY_RING_MASS_SECOND_MEASUREMENT("shearEmptyRingMassSecondMeasurement"),
//    SHEAR_RING_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT("shearRingWithWetSoilMassFirstMeasurement"),
//    SHEAR_RING_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT("shearRingWithWetSoilMassSecondMeasurement"),
//    SHEAR_RING_VOLUME_FIRST_MEASUREMENT("shearRingVolumeFirstMeasurement"),
//    SHEAR_RING_VOLUME_SECOND_MEASUREMENT("shearRingVolumeSecondMeasurement"),
//    SHEAR_RING_DENSITY_FIRST_MEASUREMENT("shearRingDensityFirstMeasurement"),
//    SHEAR_RING_DENSITY_SECOND_MEASUREMENT("shearRingDensitySecondMeasurement"),
//    SHEAR_RING_DRY_SOIL_DENSITY_FIRST_MEASUREMENT("shearRingDrySoilDensityFirstMeasurement"),
//    SHEAR_RING_DRY_SOIL_DENSITY_SECOND_MEASUREMENT("shearRingDrySoilDensitySecondMeasurement"),
//    SHEAR_RING_DENSITY_AVERAGE_FIRST_MEASUREMENT("shearRingDensityAverageFirstMeasurement"),
//    SHEAR_RING_DRY_SOIL_DENSITY_AVERAGE_FIRST_MEASUREMENT("shearRingDrySoilDensityAverageFirstMeasurement");

    private final String name;

    public static ShearDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(ShearDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
