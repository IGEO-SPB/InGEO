package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.ShearDto;
import org.geoproject.ingeo.mapper.qualifier.ShearMapperQualifier;
import org.geoproject.ingeo.models.Shear;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class,
        imports = {LocalDate.class},
uses = {ShearMapperQualifier.class}
)
public interface ShearMapper {

    @Mapping(target = "laborNumber", source = "entity.sample.laborNumber")
    @Mapping(target = "archive", source = "entity.isArchive")
    @Mapping(target = "excluded", source = "entity.isExcluded")
    @Mapping(target = "physicalPropertiesDensityBefore", ignore = true)
    @Mapping(target = "physicalPropertiesWaterContentBefore", ignore = true)
    @Mapping(target = "kdTransient", ignore = true)
    @Mapping(target = "isExcludedTransient", ignore = true)
    ShearDto shearToShearDto(Shear entity);

    List<ShearDto> shearToShearDto(List<Shear> entities);

    @Mapping(target = "sample", ignore = true)
    @Mapping(target = "averageWaterContentAfter", ignore = true)
    void updateShearFromShearDto(@MappingTarget Shear updatedObject, ShearDto sourceDto);

    default void updateShearFromShearDto(@MappingTarget List<Shear> updatedObjects, List<ShearDto> sourceDTOs) {
        for (var updatedObject : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {

                if (Objects.equals(updatedObject.getId(), sourceDTO.getId())) {
                    this.updateShearFromShearDto(updatedObject, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "sample", source = "dto.laborNumber", qualifiedByName = {"ShearMapperQualifier", "getByProjectIdAndLaborNumber"})
    @Mapping(target = "averageWaterContentAfter", ignore = true)
    @Mapping(target = "isArchive", defaultValue = "false")
//    @Mapping(target = "id", ignore = true)
    Shear shearDtoToShear(ShearDto dto);

    List<Shear> shearDtoToShear(List<ShearDto> dtos);

//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "samplingDate", ignore = true)
//    void updateWaterExtractPartialFromWaterExtractPartialDto(@MappingTarget WaterExtractPartial updatedObject, WaterExtractPartialDto waterExtractPartialDto);
//
//    default void updateWaterExtractPartialFromWaterExtractPartialDto(List<WaterExtractPartial> updatedObjects, List<WaterExtractPartialDto> sourceDTOs) {
//        for (var updatedObject : updatedObjects) {
//            for (var sourceDTO : sourceDTOs) {
//
//                if (Objects.equals(updatedObject.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
//                    this.updateWaterExtractPartialFromWaterExtractPartialDto(updatedObject, sourceDTO);
//
//                    updatedObject.setSamplingDate(sourceDTO.getSamplingDate());
//
//                    if (Objects.isNull(sourceDTO.getNO3())) {
//                        updatedObject.setNO3(null);
//                    }
//
//                    if (Objects.isNull(sourceDTO.getFe())) {
//                        updatedObject.setFe(null);
//                    }
//                }
//            }
//        }
//    }
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", source = "dto", qualifiedByName = {"WaterExtractMapperQualifier", "getBySurveyPointNumberAndLaborNumber"})
//    @Mapping(target = "isBlocked", defaultValue = "false")
//    @Mapping(target = "isArchive", defaultValue = "false")
//    @Mapping(target = "samplingDate", dateFormat = "dd.MM.yyyy")
//    WaterExtractPartial waterExtractPartialDtoToWaterExtractPartial(WaterExtractPartialDto dto);
//
//    List<WaterExtractPartial> waterExtractPartialDtoToWaterExtractPartial(List<WaterExtractPartialDto> newDtos);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", source = "dto", qualifiedByName = {"WaterExtractMapperQualifier", "getBySurveyPointNumberAndLaborNumber"})
//    @Mapping(target = "isBlocked", defaultValue = "false")
//    @Mapping(target = "isArchive", defaultValue = "false")
//    WaterExtractPartialResult waterExtractPartialResultDtoToWaterExtractPartialResult(WaterExtractPartialResultDto dto);
//
//    List<WaterExtractPartialResult> waterExtractPartialResultDtoToWaterExtractPartialResult(List<WaterExtractPartialResultDto> dtos);
//
//    @Mapping(target = "laborNumber", source = "waterExtractPartialResult.sample.laborNumber")
//    @Mapping(target = "surveyPointNumber", source = "waterExtractPartialResult.sample.surveyPoint.pointNumber")
//    @Mapping(target = "isBlockedTransient", ignore = true)
//    WaterExtractPartialResultDto waterExtractPartialResultToWaterExtractPartialResultDto(WaterExtractPartialResult waterExtractPartialResult);
//
//    List<WaterExtractPartialResultDto> waterExtractPartialResultToWaterExtractPartialResultDto(List<WaterExtractPartialResult> waterExtractPartialResultList);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "samplingDate", ignore = true)
//    void updateWaterExtractPartialResultFromWaterExtractPartialResultDto(@MappingTarget WaterExtractPartialResult updatedObject, WaterExtractPartialResultDto sourceDto);
//
//    default void updateWaterExtractPartialResultFromWaterExtractPartialResultDto(@MappingTarget List<WaterExtractPartialResult> updatedObjects, List<WaterExtractPartialResultDto> sourceDtos) {
//        for (var updatedObject : updatedObjects) {
//            for (var sourceDto : sourceDtos) {
//
//                if (Objects.equals(updatedObject.getSample().getLaborNumber(), sourceDto.getLaborNumber())) {
//                    this.updateWaterExtractPartialResultFromWaterExtractPartialResultDto(updatedObject, sourceDto);
//
//                    if (Objects.isNull(sourceDto.getNO3Txt())) {
//                        updatedObject.setNO3Txt(null);
//                    }
//
//                    if (Objects.isNull(sourceDto.getFeTxt())) {
//                        updatedObject.setFeTxt(null);
//                    }
//                }
//            }
//        }
//    }
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "isArchive", ignore = true)
//    @Mapping(target = "isBlocked", ignore = true)
////    @Mapping(target = "pH", ignore = true)
////    @Mapping(target = "Cl", ignore = true)
////    @Mapping(target = "NO3Txt", ignore = true)
////    @Mapping(target = "FeTxt", ignore = true)
////    @Mapping(target = "gum", ignore = true)
////    @Mapping(target = "SO4", ignore = true)
////    @Mapping(target = "Cl_v", ignore = true)
////    @Mapping(target = "SO4_v", ignore = true)
//    @Mapping(target = "cl", ignore = true)
//    @Mapping(target = "NO3Txt", ignore = true)
//    @Mapping(target = "feTxt", ignore = true)
//    @Mapping(target = "gum", ignore = true)
//    @Mapping(target = "SO4", ignore = true)
//    @Mapping(target = "cl_v", ignore = true)
//    @Mapping(target = "SO4_v", ignore = true)
//    @Mapping(target = "clSO4", ignore = true)
//
//    @Mapping(target = "samplingDate", ignore = true)
//    void updateWaterExtractPartialResultFromWaterExtractPartial(@MappingTarget WaterExtractPartialResult resultObject, WaterExtractPartial updatedObject);
//
//    @Mapping(target = "laborNumber", source = "waterExtractFull.sample.laborNumber")
//    @Mapping(target = "surveyPointNumber", source = "waterExtractFull.sample.surveyPoint.pointNumber")
//    WaterExtractFullDto waterExtractFullToWaterExtractFullDto(WaterExtractFull waterExtractFull);
//
//    List<WaterExtractFullDto> waterExtractFullToWaterExtractFullDto(List<WaterExtractFull> entitiesByProject);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "samplingDate", ignore = true)
//    void updateWaterExtractFullFromWaterExtractFullDto(@MappingTarget WaterExtractFull updatedObject, WaterExtractFullDto sourceDto);
//
//    default void updateWaterExtractFullFromWaterExtractFullDto(@MappingTarget List<WaterExtractFull> updatedObjects, List<WaterExtractFullDto> sourceDtos) {
//        for (var updatedObject : updatedObjects) {
//            for (var sourceDto : sourceDtos) {
//
//                if (Objects.equals(updatedObject.getSample().getLaborNumber(), sourceDto.getLaborNumber())) {
//                    this.updateWaterExtractFullFromWaterExtractFullDto(updatedObject, sourceDto);
//                }
//            }
//        }
//    }
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "samplingDate", ignore = true)
//    void updateWaterExtractFullResultFromWaterExtractFullResultDto(@MappingTarget WaterExtractFullResult updatedObject, WaterExtractFullResultDto sourceDto);
//
//    default void updateWaterExtractFullResultFromWaterExtractFullResultDto(@MappingTarget List<WaterExtractFullResult> updatedObjects, List<WaterExtractFullResultDto> sourceDtos) {
//        for (var updatedObject : updatedObjects) {
//            for (var sourceDto : sourceDtos) {
//
//                if (Objects.equals(updatedObject.getSample().getLaborNumber(), sourceDto.getLaborNumber())) {
//                    this.updateWaterExtractFullResultFromWaterExtractFullResultDto(updatedObject, sourceDto);
//                }
//            }
//        }
//    }
//
//    @Mapping(target = "isArchive", ignore = true)
//    @Mapping(target = "isBlocked", ignore = true)
//    @Mapping(target = "samplingDate", ignore = true)
//
//    @Mapping(target = "HCO3_v", ignore = true)
//    @Mapping(target = "HCO3_eq", ignore = true)
//    @Mapping(target = "HCO3_proc", ignore = true)
//    @Mapping(target = "CO3_v", ignore = true)
//    @Mapping(target = "CO3_eq", ignore = true)
//    @Mapping(target = "CO3_proc", ignore = true)
//    @Mapping(target = "cl_v", ignore = true)
//    @Mapping(target = "cl_eq", ignore = true)
//    @Mapping(target = "cl_proc", ignore = true)
//    @Mapping(target = "SO4_v", ignore = true)
//    @Mapping(target = "SO4_eq", ignore = true)
//    @Mapping(target = "SO4_proc", ignore = true)
//    @Mapping(target = "anSumEq", ignore = true)
//    @Mapping(target = "anSumProc", ignore = true)
//    @Mapping(target = "ca_v", ignore = true)
//    @Mapping(target = "ca_eq", ignore = true)
//    @Mapping(target = "ca_proc", ignore = true)
//    @Mapping(target = "mg_v", ignore = true)
//    @Mapping(target = "mg_eq", ignore = true)
//    @Mapping(target = "mg_proc", ignore = true)
//    @Mapping(target = "na_v", ignore = true)
//    @Mapping(target = "na_eq", ignore = true)
//    @Mapping(target = "na_proc", ignore = true)
//    @Mapping(target = "katSumEq", ignore = true)
//    @Mapping(target = "katSumProc", ignore = true)
//    @Mapping(target = "ionSum", ignore = true)
//    @Mapping(target = "dry", ignore = true)
//    @Mapping(target = "o2", ignore = true)
//    @Mapping(target = "PH", ignore = true)
//    @Mapping(target = "gum", ignore = true)
//    void updateWaterExtractFullResultFromWaterExtractFull(@MappingTarget WaterExtractFullResult resultObject, WaterExtractFull updatedObject);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", source = "dto", qualifiedByName = {"WaterExtractMapperQualifier", "getBySurveyPointNumberAndLaborNumber"})
//    @Mapping(target = "isBlocked", defaultValue = "false")
//    @Mapping(target = "isArchive", defaultValue = "false")
//    WaterExtractFull waterExtractFullDtoToWaterExtractFull(WaterExtractFullDto dto);
//
//    List<WaterExtractFull> waterExtractFullDtoToWaterExtractFull(List<WaterExtractFullDto> dtos);
//
//    @Mapping(target = "laborNumber", source = "waterExtractFullResult.sample.laborNumber")
//    @Mapping(target = "surveyPointNumber", source = "waterExtractFullResult.sample.surveyPoint.pointNumber")
//    @Mapping(target = "isBlockedTransient", ignore = true)
//    WaterExtractFullResultDto waterExtractFullResultToWaterExtractFullResultDto(WaterExtractFullResult waterExtractFullResult);
//
//    List<WaterExtractFullResultDto> waterExtractFullResultToWaterExtractFullResultDto(List<WaterExtractFullResult> waterExtractFullResultList);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", source = "dto", qualifiedByName = {"WaterExtractMapperQualifier", "getBySurveyPointNumberAndLaborNumber"})
//    @Mapping(target = "isBlocked", defaultValue = "false")
//    @Mapping(target = "isArchive", defaultValue = "false")
//    WaterExtractFullResult waterExtractFullResultDtoToWaterExtractFullResult(WaterExtractFullResultDto dto);
//
//    List<WaterExtractFullResult> waterExtractFullResultDtoToWaterExtractFullResult(List<WaterExtractFullResultDto> dtos);
}
