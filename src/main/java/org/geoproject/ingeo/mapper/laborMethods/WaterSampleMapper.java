package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleDto;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterSample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class,
imports = {SurveyPoint.class})
public interface WaterSampleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "surveyPoint", ignore = true)
    @Mapping(target = "isArchive", ignore = true)
    void updateWaterSampleFromWaterSampleDto(@MappingTarget WaterSample waterSample, WaterSampleDto waterSampleDto);

    default void updateWaterSampleFromWaterSampleDto(List<WaterSample> updatedObjects, List<WaterSampleDto> sourceDTOs) {
        for (var updatedObject : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(updatedObject.getLaborNumber(), sourceDTO.getLaborNumber())) {
                    this.updateWaterSampleFromWaterSampleDto(updatedObject, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "surveyPointId", source = "waterSample.surveyPoint.id")
    @Mapping(target = "blockedFromWaterSampleResult", source = "waterSample.blockedFromWaterSampleResult")
    WaterSampleDto waterSampleToWaterSampleDto(WaterSample waterSample);

    List<WaterSampleDto> waterSampleToWaterSampleDto(List<WaterSample> dtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "surveyPoint", expression = "java(new SurveyPoint(dto.getSurveyPointId()))")
    @Mapping(target = "RHCO3", ignore = true)
    @Mapping(target = "RCO3", ignore = true)
    @Mapping(target = "RCL", ignore = true)
    @Mapping(target = "RNO2", ignore = true)
    @Mapping(target = "RNO3", ignore = true)
    @Mapping(target = "RCa", ignore = true)
    @Mapping(target = "RMg", ignore = true)
    @Mapping(target = "RNH4", ignore = true)
    @Mapping(target = "RFe", ignore = true)
    @Mapping(target = "RSiO2", ignore = true)
    @Mapping(target = "RO2", ignore = true)
    @Mapping(target = "RCO2sv", ignore = true)
    @Mapping(target = "RCO2ag", ignore = true)
    @Mapping(target = "RH2S", ignore = true)
    @Mapping(target = "isArchive", constant = "false")
    WaterSample waterSampleDtoToWaterSample(WaterSampleDto dto);

    List<WaterSample> waterSampleDtoToWaterSample(List<WaterSampleDto> dtos);
}
