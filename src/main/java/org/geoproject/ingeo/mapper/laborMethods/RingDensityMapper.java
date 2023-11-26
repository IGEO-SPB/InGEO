package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.RingDensityDTO;
import org.geoproject.ingeo.models.labor.RingDensity;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface RingDensityMapper {

    @Mapping(target = "laborNumber", source = "source.sample.laborNumber")
    @Mapping(target = "liquidityIndex", ignore = true)
    RingDensityDTO ringDensityToRingDensityDTO(RingDensity source);

    List<RingDensityDTO> tToY(List<RingDensity> source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    default void updateRingDensity(@MappingTarget List<RingDensity> updatedObjects, List<RingDensityDTO> sourceDTOs) {
        for (var object : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(object.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updateRingDensity(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateRingDensity(@MappingTarget RingDensity updatedObject, RingDensityDTO sourceDTO);

    @Mapping(target = "sample", source = "updateData")
    @Mapping(target = "ringNumberFirstMeasurement", ignore = true)
    @Mapping(target = "ringNumberSecondMeasurement", ignore = true)
    @Mapping(target = "emptyRingMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "emptyRingMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "ringWithWetSoilMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "ringWithWetSoilMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "ringVolumeFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "ringVolumeSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "ringDensityFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "ringDensitySecondMeasurement", expression = "java(0f)")
    @Mapping(target = "ringDensityAverage", expression = "java(0f)")
    @Mapping(target = "ringDrySoilDensity", expression = "java(0f)")
    @Mapping(target = "fullWaterContent", expression = "java(0f)")
    void updateRingDensityFromSample(@MappingTarget RingDensity updatedObject, Sample updateData);
}
