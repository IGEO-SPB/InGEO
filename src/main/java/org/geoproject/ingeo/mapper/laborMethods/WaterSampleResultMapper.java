package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;
import org.geoproject.ingeo.exceptions.ConflictException;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.WaterSampleResult;
import org.geoproject.ingeo.models.classificators.WaterGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.geoproject.ingeo.exceptions.ExceptionTypeEnum.NOT_ALL_FIELDS_FILLED_EXCEPTION;

@Mapper(config = MapStructConfiguration.class,
imports = {LocalDate.class, WaterGroup.class})
public interface WaterSampleResultMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "surveyPoint", ignore = true)
    @Mapping(target = "isArchive", ignore = true)
    @Mapping(target = "samplingDate", defaultExpression = "java(LocalDate.now())")
    @Mapping(target = "laboratoryAcceptanceDate", defaultExpression = "java(LocalDate.now())")
    void updateWaterSampleFromWaterSampleDto(@MappingTarget WaterSampleResult updatedObject, WaterSampleResultDto sourceDto);

    default void updateWaterSampleFromWaterSampleDto(List<WaterSampleResult> updatedObjects, List<WaterSampleResultDto> sourceDTOs) {
        for (var updatedObject : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(updatedObject.getLaborNumber(), sourceDTO.getLaborNumber())) {
                    this.updateWaterSampleFromWaterSampleDto(updatedObject, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "surveyPoint", ignore = true)
    @Mapping(target = "isArchive", ignore = true)
    WaterSampleResult waterSampleResultDtoToWaterSampleResult(WaterSampleResultDto sourceDto);

    default List<WaterSampleResult> waterSampleResultDtoToWaterSampleResult(List<WaterSampleResultDto> sourceDtos, Set<SurveyPoint> surveyPoints) {
        System.out.println("surveyPoints");
        System.out.println(surveyPoints.isEmpty());
        System.out.println(surveyPoints.size());
        surveyPoints.forEach(sp -> System.out.println(sp.getPointNumber()));

        return sourceDtos.stream()
                .map(sourceDto -> {

                    try {
                        WaterSampleResult waterSampleResult = waterSampleResultDtoToWaterSampleResult(sourceDto);

                        SurveyPoint surveyPointToSet = surveyPoints.stream()
                                .filter(surveyPoint -> surveyPoint.getPointNumber().equals(sourceDto.getSurveyPointNumber()))
                                .findFirst()
                                .orElseThrow(() -> new ConflictException("Не найдена соответствующая выработка"));

                        waterSampleResult.setSurveyPoint(surveyPointToSet);

                        return waterSampleResult;

                    } catch (NullPointerException nullPointerException) {
                        nullPointerException.printStackTrace();
                        throw new ConflictException(NOT_ALL_FIELDS_FILLED_EXCEPTION.getMessage());
                    }
                })
                .toList();
    }

    @Mapping(target = "surveyPointNumber", source = "sourceEntity.surveyPoint.pointNumber")
    @Mapping(target = "isBlockedTransient", ignore = true)
    WaterSampleResultDto waterSampleResultToWaterSampleResultDto(WaterSampleResult sourceEntity);

    List<WaterSampleResultDto> waterSampleResultToWaterSampleResultDto(List<WaterSampleResult> entitiesBySurveyPoint);
}
