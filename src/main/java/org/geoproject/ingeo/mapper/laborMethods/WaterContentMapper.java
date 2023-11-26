package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.WaterContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface WaterContentMapper {

    @Mapping(target = "laborNumber", source = "source.sample.laborNumber")
    WaterContentDTO waterContentToWaterContentDTO(WaterContent source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    default void updateWaterContent(@MappingTarget List<WaterContent> updatedObjects, List<WaterContentDTO> sourceDTOs) {
        for (var object : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(object.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updateWaterContent(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateWaterContent(@MappingTarget WaterContent updatedObject, WaterContentDTO sourceDTO);

    @Mapping(target = "sample", source = "updateData")
//    @Mapping(target = "floatField", source = "floatField", defaultExpression = "java(0f)")
//    @Mapping(target = "naturalWaterContentWeighingBottleNumberFirstMeasurement", defaultExpression = "java(0f)")
    @Mapping(target = "naturalWaterContentWeighingBottleNumberFirstMeasurement", ignore = true)
    @Mapping(target = "naturalWaterContentWeighingBottleNumberSecondMeasurement", ignore = true)
    @Mapping(target = "liquidityWeighingBottleNumberFirstMeasurement", ignore = true)
    @Mapping(target = "liquidityWeighingBottleNumberSecondMeasurement", ignore = true)
    @Mapping(target = "plasticWeighingBottleNumberFirstMeasurement", ignore = true)
    @Mapping(target = "plasticWeighingBottleNumberSecondMeasurement", ignore = true)
    @Mapping(target = "naturalWaterContentEmptyWeighingBottleMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalWaterContentEmptyWeighingBottleMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityEmptyWeighingBottleMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityEmptyWeighingBottleMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticEmptyWeighingBottleMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticEmptyWeighingBottleMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalWaterContentWeighingBottleWithWetSoilMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalWaterContentWeighingBottleWithWetSoilMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityWeighingBottleWithWetSoilMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityWeighingBottleWithWetSoilMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticWeighingBottleWithWetSoilMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticWeighingBottleWithWetSoilMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalWaterContentWeighingBottleWithDrySoilMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalWaterContentWeighingBottleWithDrySoilMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityWeighingBottleWithDrySoilMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityWeighingBottleWithDrySoilMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticWeighingBottleWithDrySoilMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticWeighingBottleWithDrySoilMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalWaterContentWeighingBottleWaterContentFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalWaterContentWeighingBottleWaterContentSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityWeighingBottleWaterContentFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "liquidityWeighingBottleWaterContentSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticWeighingBottleWaterContentFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "plasticWeighingBottleWaterContentSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "naturalAverageWaterContent", expression = "java(0f)")
    @Mapping(target = "averageLiquidLimit", expression = "java(0f)")
    @Mapping(target = "averagePlasticLimit", expression = "java(0f)")
    void updateWaterContentFromSample(@MappingTarget WaterContent updatedObject, Sample updateData);
}