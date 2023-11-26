package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.AreometryDTO;
import org.geoproject.ingeo.models.Areometry;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface AreometryMapper {

    @Mapping(target = "laborNumber", ignore = true)
    @Mapping(target = "voidRatio", ignore = true)
    @Mapping(target = "structure", ignore = true)
    AreometryDTO areometryToAreometryDTO(Areometry source);

    List<AreometryDTO> areometryToAreometryDTO(List<Areometry> source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    default void updateEntity(@MappingTarget List<Areometry> updatedObjects, List<AreometryDTO> sourceDTOs) {
        for (var object : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(object.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updateAreometry(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateAreometry(@MappingTarget Areometry updatedObject, AreometryDTO sourceDTO);

    @Mapping(target = "sample", ignore = true)
    @Mapping(target = "isSand", ignore = true)
    @Mapping(target = "totalSubsample", ignore = true)
    @Mapping(target = "undisturbedSampleWaterContent", ignore = true)
    @Mapping(target = "subsampleWetSoil", ignore = true)
    @Mapping(target = "weighingBottleNumber", ignore = true)
    @Mapping(target = "emptyWeighingBottleMass", ignore = true)
    @Mapping(target = "weighingBottleWithWetSoilMass", ignore = true)
    @Mapping(target = "weighingBottleWithDrySoilMass", ignore = true)
    @Mapping(target = "particleMassOver10mm", ignore = true)
    @Mapping(target = "particleMassBetween10and5mm", ignore = true)
    @Mapping(target = "particleMassBetween5and2mm", ignore = true)
    @Mapping(target = "particleMassBetween2and1mm", ignore = true)
    @Mapping(target = "particleMassBetween1and05mm", ignore = true)
    @Mapping(target = "particleMassBetween05and025mm", ignore = true)
    @Mapping(target = "particleMassBetween025and01mm", ignore = true)
    @Mapping(target = "totalReadingForParticleSizeBetween005and001", ignore = true)
    @Mapping(target = "totalReadingForParticleSizeBetween001and0002", ignore = true)
    @Mapping(target = "totalReadingForParticleSizeBetweenLess0002", ignore = true)
    @Mapping(target = "totalReadingForParticleSizeNumberThree", ignore = true)
    @Mapping(target = "firstAmendment", ignore = true)
    @Mapping(target = "secondAmendment", ignore = true)
    @Mapping(target = "thirdAmendment", ignore = true)
    @Mapping(target = "fourthAmendment", ignore = true)
    void updateAreometryFromSample(@MappingTarget Areometry updatedObject, Sample updateData);
}