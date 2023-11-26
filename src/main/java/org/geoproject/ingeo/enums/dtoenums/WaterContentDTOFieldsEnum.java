package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;

import java.util.Arrays;

@Getter
//@RequiredArgsConstructor
public enum WaterContentDTOFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT("naturalWaterContentWeighingBottleNumberFirstMeasurement"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT("naturalWaterContentWeighingBottleNumberSecondMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT("liquidityWeighingBottleNumberFirstMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT("liquidityWeighingBottleNumberSecondMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_NUMBER_FIRST_MEASUREMENT("plasticWeighingBottleNumberFirstMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_NUMBER_SECOND_MEASUREMENT("plasticWeighingBottleNumberSecondMeasurement"),
    NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT("naturalWaterContentEmptyWeighingBottleMassFirstMeasurement"),
    NATURAL_WATER_CONTENT_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT("naturalWaterContentEmptyWeighingBottleMassSecondMeasurement"),
    LIQUIDITY_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT("liquidityEmptyWeighingBottleMassFirstMeasurement"),
    LIQUIDITY_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT("liquidityEmptyWeighingBottleMassSecondMeasurement"),
    PLASTIC_EMPTY_WEIGHING_BOTTLE_MASS_FIRST_MEASUREMENT("plasticEmptyWeighingBottleMassFirstMeasurement"),
    PLASTIC_EMPTY_WEIGHING_BOTTLE_MASS_SECOND_MEASUREMENT("plasticEmptyWeighingBottleMassSecondMeasurement"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT("naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT("naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT("liquidityWeighingBottleWithWetSoilMassFirstMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT("liquidityWeighingBottleWithWetSoilMassSecondMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_FIRST_MEASUREMENT("plasticWeighingBottleWithWetSoilMassFirstMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_WITH_WET_SOIL_MASS_SECOND_MEASUREMENT("plasticWeighingBottleWithWetSoilMassSecondMeasurement"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT("naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT("naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT("liquidityWeighingBottleWithDrySoilMassFirstMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT("liquidityWeighingBottleWithDrySoilMassSecondMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_FIRST_MEASUREMENT("plasticWeighingBottleWithDrySoilMassFirstMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_WITH_DRY_SOIL_MASS_SECOND_MEASUREMENT("plasticWeighingBottleWithDrySoilMassSecondMeasurement"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WATER_CONTENT_FIRST_MEASUREMENT("naturalWaterContentWeighingBottleWaterContentFirstMeasurement"),
    NATURAL_WATER_CONTENT_WEIGHING_BOTTLE_WATER_CONTENT_SECOND_MEASUREMENT("naturalWaterContentWeighingBottleWaterContentSecondMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_WATER_CONTENT_FIRST_MEASUREMENT("liquidityWeighingBottleWaterContentFirstMeasurement"),
    LIQUIDITY_WEIGHING_BOTTLE_WATER_CONTENT_SECOND_MEASUREMENT("liquidityWeighingBottleWaterContentSecondMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_WATER_CONTENT_FIRST_MEASUREMENT("plasticWeighingBottleWaterContentFirstMeasurement"),
    PLASTIC_WEIGHING_BOTTLE_WATER_CONTENT_SECOND_MEASUREMENT("plasticWeighingBottleWaterContentSecondMeasurement"),
    NATURAL_AVERAGE_WATER_CONTENT("naturalAverageWaterContent"),
    AVERAGE_LIQUID_LIMIT("averageLiquidLimit"),
    AVERAGE_PLASTIC_LIMIT("averagePlasticLimit"),
    PLASTICITY_INDEX("plasticityIndex"),
    LIQUIDITY_INDEX("liquidityIndex");

    private final String name;

    WaterContentDTOFieldsEnum(String name) {
        this.name = name;
    }

    public static WaterContentDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(WaterContentDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}