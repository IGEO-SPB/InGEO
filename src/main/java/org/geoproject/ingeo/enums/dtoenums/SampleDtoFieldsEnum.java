package org.geoproject.ingeo.enums.dtoenums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum SampleDtoFieldsEnum {
    ID("id"),
    SURVEY_POINT("surveyPoint"),
    LABOR_NUMBER("laborNumber"),
    DEPTH_MIN("depthMin"),
    DEPTH_MAX("depthMax"),
    AVERAGE_DENSITY("averageDensity"),
    SUBTERRANEAN_WATERS_LEVEL("subterraneanWatersLevel"),
    STRUCTURE("structure"),
    IS_SAND("isSand"),
    PARTICLE_DENSITY("particleDensity"),
    BULK_DENSITY("bulkDensity"),
    DRY_SOIL_DENSITY("drySoilDensity"),
    DRY_SOIL_DENSITY_FOR_SAND_MAXIMAL("drySoilDensityForSandMaximal"),
    DRY_SOIL_DENSITY_FOR_SAND_MINIMAL("drySoilDensityForSandMinimal"),
    VOID_RATIO("voidRatio"),
    NATURAL_WATER_CONTENT("naturalWaterContent"),
    SATURATION_RATIO("saturationRatio"),
    FULL_WATER_CONTENT("fullWaterContent"),
    AVERAGE_LIQUID_LIMIT("averageLiquidLimit"),
    AVERAGE_PLASTIC_LIMIT("averagePlasticLimit"),
    PLASTICITY_INDEX("plasticityIndex"),
    LIQUIDITY_INDEX("liquidityIndex"),
    BOYCHENKO_CONE_UNDISTURBED_STR_IMMERSION_DEPTH_AVERAGE("boychenkoConeUndisturbedStrImmersionDepthAverage"),
    BOYCHENKO_CONE_BROKEN_STR_IMMERSION_DEPTH_AVERAGE("boychenkoConeBrokenStrImmersionDepthAverage"),
    MAXIMAL_BULK_DENSITY("maximalBulkDensity"),
    MAXIMAL_DRY_SOIL_DENSITY("maximalDrySoilDensity"),
    OPTIMAL_MOISTURE("optimalMoisture"),
    COEFFICIENT_PERMEABILITY("coefficientPermeability"),
    ROD_KF("rodKf"),
    REPOSE_ANGLE_WHEN_DRY("reposeAngleWhenDry"),
    REPOSE_ANGLE_UNDER_WATER("reposeAngleUnderWater"),
    ORGANIC_CONTENT("organicContent"),
    DECOMPOSITION_DEGREE("decompositionDegree"),
    ASH_CONTENT("ashContent"),
    TURING_HUMUS("turingHumus"),
    SOIL_SOFTENING_RATIO("soilSofteningRatio"),
    ROCK_DECOMPOSITION_INDEX("rockDecompositionIndex"),
    SOIL_TYPE("soilType"),
    CRUSHABILITY("crushability"),
    ABRASION_RESISTANCE("abrasionResistance"),
    FROST_RESISTANCE_GRADE_ONE("frostResistanceGradeOne"),
    FROST_RESISTANCE_GRADE_TWO("frostResistanceGradeTwo"),
    SOIL_SPECIFIC_ELECTRICAL_RESISTANCE("soilSpecificElectricalResistance"),
    CATHODE_CURRENT_DENSITY("cathodeCurrentDensity"),
    PASSPORT_ATTACHMENT("passportAttachment"),
    PASSPORT_LIST_NUMBER("passportListNumber"),
    PASS_LIST_CFIKN("passListCFIKN"),
    PASS_LIST_CFIKD("passListCFIKD"),
    PASS_ATTACH_CFIKN("passAttachCFIKN"),
    PASS_ATTACH_CFIKD("passAttachCFIKD"),
    SOIL_TYPE_FOR_PASSPORT("soilTypeForPassport"),
    IS_ARCHIVE("isArchive"),
    AREOMETRY("areometry"),
    GRAN_COMPOSITION_AREOMETRY("granCompositionAreometry"),
    BOYCHENKO_CONE("boychenkoCone"),
    CONSTRUCTION_MESH("constructionMesh"),
    GRAN_COMPOSITION_CONSTRUCTION_MESH("granCompositionConstructionMesh"),
    DENSITY("density"),
    ORGANIC_MATTER("organicMatter"),
    PHYSICAL_PROPERTIES("physicalProperties"),
    RING_DENSITY("ringDensity"),
    WATER_CONTENT("waterContent");

    private final String name;

    public static SampleDtoFieldsEnum getEnumByName(String name) {
        return Arrays.stream(SampleDtoFieldsEnum.values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum name: " + name));
    }
}
