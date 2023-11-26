package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.DensityDTO;
import org.geoproject.ingeo.models.labor.Density;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface DensityMapper {

    @Mapping(target = "laborNumber", source = "source.sample.laborNumber")
    DensityDTO densityToDensityDTO(Density source);

    default void updateDensity(@MappingTarget List<Density> updatedObjects, List<DensityDTO> sourceDTOs) {
        for (var object : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(object.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updateDensity(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateDensity(@MappingTarget Density updatedObject, DensityDTO sourceDTO);

    @Mapping(target = "sample", source = "updateData")
    @Mapping(target = "pycnometerWeightWithDrySoilFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "emptyPycnometerWeightFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "pycnometerWeightWithWaterFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "pycnometerWeightWithSoilAndWaterFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "drySoilWeightFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "soilVolumeFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "soilDensityFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "pycnometerWeightWithDrySoilSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "emptyPycnometerWeightSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "pycnometerWeightWithWaterSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "pycnometerWeightWithSoilAndWaterSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "drySoilWeightSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "soilVolumeSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "soilDensitySecondMeasurement", expression = "java(0f)")
    void updateDensityFromSample(@MappingTarget Density updatedObject, Sample updateData);
}
