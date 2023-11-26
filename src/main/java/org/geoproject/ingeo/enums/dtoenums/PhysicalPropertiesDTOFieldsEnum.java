package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;

import java.util.Arrays;

@Getter
//@RequiredArgsConstructor
public enum PhysicalPropertiesDTOFieldsEnum {
    LABOR_NUMBER("laborNumber"),
    SURVEY_POINT_NUMBER("surveyPointNumber"),
    DEPTH_MIN("depthMin"),
    DEPTH_MAX("depthMax"),
    AVERAGE_DENSITY("averageDensity"),
    RING_DENSITY_AVERAGE("ringDensityAverage"),
    RING_DRY_SOIL_DENSITY("ringDrySoilDensity"),
    VOID_RATIO("voidRatio"),
    NATURAL_AVERAGE_WATER_CONTENT("naturalAverageWaterContent"),
    SATURATION_RATIO("saturationRatio"),
    FULL_WATER_CONTENT("fullWaterContent"),
    AVERAGE_LIQUID_LIMIT("averageLiquidLimit"),
    AVERAGE_PLASTIC_LIMIT("averagePlasticLimit"),
    PLASTICITY_INDEX("plasticityIndex"),
    PLASTICITY_SOIL_SUBTYPE("plasticitySoilSubType"),
    LIQUIDITY_INDEX("liquidityIndex"),
    LIQUIDITY_SOIL_SUBTYPE("liquiditySoilSubType"),
    CLAY_SOIL_SUBTYPE("claySoilSubType"),
    UNDISTURBED_STR_BOYCHENKO_CONE_IMMERSION_DEPTH_AVERAGE("undisturbedStrBoychenkoConeImmersionDepthAverage"),
    UNDISTURBED_STRUCTURE_CB_LIQUIDITY_INDEX_NAME("undisturbedStructureCbLiquidityIndexName"),
    UNDISTURBED_STRUCTURE_CB_CONSISTENCY("undisturbedStructureCbConsistency"),
    BROKEN_STR_BOYCHENKO_CONE_IMMERSION_DEPTH_AVERAGE("brokenStrBoychenkoConeImmersionDepthAverage"),
    BROKEN_STRUCTURE_CB_LIQUIDITY_INDEX_NAME("brokenStructureCbLiquidityIndexName"),
    BROKEN_STRUCTURE_CB_CONSISTENCY("brokenStructureCbConsistency"),
    CB_CONSISTENCY_DIFFERENCE("cbConsistencyDifference"),
    STRUCTURAL_STRENGTH_DEGREE("structuralStrengthDegree"),
    IGNITION_LOSS_AVERAGE_MASS("ignitionLossAverageMass"),
    IGNITION_LOSS_AVERAGE_NAME("ignitionLossAverageName"),
    DECOMPOSITION_DEGREE("decompositionDegree"),
    DECOMPOSITION_DEGREE_NAME("decompositionDegreeName");

    private final String name;

    PhysicalPropertiesDTOFieldsEnum(String name) {
        this.name = name;
    }

    public static PhysicalPropertiesDTOFieldsEnum getEnumByName(String name) {
        return Arrays.stream(PhysicalPropertiesDTOFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}