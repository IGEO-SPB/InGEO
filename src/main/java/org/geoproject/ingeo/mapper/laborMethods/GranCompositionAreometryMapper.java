package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.GranCompositionDTO;
import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface GranCompositionAreometryMapper {

    @Mapping(target = "surveyPointNumber", ignore = true)
    @Mapping(target = "depthMin", ignore = true)
    @Mapping(target = "depthMax", ignore = true)
    @Mapping(target = "laborNumber", source = "source.sample.laborNumber")
    @Mapping(target = "isSand", ignore = true)
    GranCompositionDTO granCompositionAreometryToGranCompositionDTO(GranCompositionAreometry source);

    List<GranCompositionDTO> granCompositionAreometryToGranCompositionDTO(List<GranCompositionAreometry> source);

//    @Mapping(target = "voidRatio", ignore = true)
//    @Mapping(target = "structure", ignore = true)
//    AreometryDTO granCompositionAreometryToAreometricDTO(GranCompositionAreometry granCompositionAreometry);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "laborNumber", ignore = true)
//    @Mapping(target = "included_more_2", ignore = true)
//    GranCompositionAreometry updateGranCompositionAreometryFromAreometricDTO(@MappingTarget GranCompositionAreometry granCompositionAreometry,
//                                          AreometryDTO areometryDTO);

    default void updateGranCompositionAreometryFromSample(@MappingTarget GranCompositionAreometry updatedGranCompositionAreometry, Sample updateData) {
        updatedGranCompositionAreometry.setSample(updateData);
//        updatedGranCompositionAreometry.setLaborNumber(updateData.getLaborNumber());
//        updatedGranCompositionAreometry.setParticleDensity(updateData.getAverageDensity());
//        updatedGranCompositionAreometry.setIsSand(updateData.isSand());
    }

//    @Override
//    GranCompositionAreometry updateTFromY(GranCompositionAreometry updatedObject, GranCompositionDTO sourceDTO);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "laborNumber", ignore = true)
//    @Mapping(target = "particleDensity", ignore = true)
//    @Mapping(target = "totalSubsample", ignore = true)
//    @Mapping(target = "undisturbedSampleWaterContent", ignore = true)
//    @Mapping(target = "subsampleWetSoil", ignore = true)
//    @Mapping(target = "weighingBottleNumber", ignore = true)
//    @Mapping(target = "emptyWeighingBottleMass", ignore = true)
//    @Mapping(target = "weighingBottleWithWetSoilMass", ignore = true)
//    @Mapping(target = "weighingBottleWithDrySoilMass", ignore = true)
//    @Mapping(target = "particleMassOver10mm", ignore = true)
//    @Mapping(target = "particleMassBetween10and5mm", ignore = true)
//    @Mapping(target = "particleMassBetween5and2mm", ignore = true)
//    @Mapping(target = "particleMassBetween2and1mm", ignore = true)
//    @Mapping(target = "particleMassBetween1and05mm", ignore = true)
//    @Mapping(target = "particleMassBetween05and025mm", ignore = true)
//    @Mapping(target = "particleMassBetween025and01mm", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeBetween005and001", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeBetween001and0002", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeBetweenLess0002", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeNumberThree", ignore = true)
//    @Mapping(target = "firstAmendment", ignore = true)
//    @Mapping(target = "secondAmendment", ignore = true)
//    @Mapping(target = "thirdAmendment", ignore = true)
//    @Mapping(target = "fourthAmendment", ignore = true)
//    void updateTFromY(@MappingTarget GranCompositionAreometry granCompositionAreometry,
//                                                                                  GranCompositionDTO granCompositionDTO);

    default void updateGranCompositionAreometry(List<GranCompositionAreometry> updatedObjects,
                                                List<GranCompositionDTO> sourceDTOs) {
        for (var object : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(object.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updateGranCompositionAreometry(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "laborNumber", ignore = true)
//    @Mapping(target = "particleDensity", ignore = true)
//    @Mapping(target = "totalSubsample", ignore = true)
//    @Mapping(target = "undisturbedSampleWaterContent", ignore = true)
//    @Mapping(target = "subsampleWetSoil", ignore = true)
//    @Mapping(target = "weighingBottleNumber", ignore = true)
//    @Mapping(target = "emptyWeighingBottleMass", ignore = true)
//    @Mapping(target = "weighingBottleWithWetSoilMass", ignore = true)
//    @Mapping(target = "weighingBottleWithDrySoilMass", ignore = true)
//    @Mapping(target = "particleMassOver10mm", ignore = true)
//    @Mapping(target = "particleMassBetween10and5mm", ignore = true)
//    @Mapping(target = "particleMassBetween5and2mm", ignore = true)
//    @Mapping(target = "particleMassBetween2and1mm", ignore = true)
//    @Mapping(target = "particleMassBetween1and05mm", ignore = true)
//    @Mapping(target = "particleMassBetween05and025mm", ignore = true)
//    @Mapping(target = "particleMassBetween025and01mm", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeBetween005and001", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeBetween001and0002", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeBetweenLess0002", ignore = true)
//    @Mapping(target = "totalReadingForParticleSizeNumberThree", ignore = true)
//    @Mapping(target = "firstAmendment", ignore = true)
//    @Mapping(target = "secondAmendment", ignore = true)
//    @Mapping(target = "thirdAmendment", ignore = true)
//    @Mapping(target = "fourthAmendment", ignore = true)
    void updateGranCompositionAreometry(@MappingTarget GranCompositionAreometry updatedObject, GranCompositionDTO sourceDTO);

//    void updateGranCompositionAreometryFromGranCompositionDTO(@MappingTarget GranCompositionAreometry byLaborNumber, GranCompositionDTO granCompositionDTO);

//    GranCompositionDTO granCompositionAreometryToGranCompositionDTO(GranCompositionAreometry bySampleId);
}