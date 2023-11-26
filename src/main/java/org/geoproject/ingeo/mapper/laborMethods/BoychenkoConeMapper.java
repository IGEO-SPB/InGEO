package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.BoychenkoConeDTO;
import org.geoproject.ingeo.models.labor.BoychenkoCone;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface BoychenkoConeMapper {

    @Mapping(target = "laborNumber", source = "source.sample.laborNumber")
    BoychenkoConeDTO boychenkoConeToBoychenkoConeDTO(BoychenkoCone source);

    default void updateBoychenkoCone(@MappingTarget List<BoychenkoCone> updatedObjects, List<BoychenkoConeDTO> sourceDTOs) {
        for (var object : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(object.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updateBoychenkoCone(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateBoychenkoCone(@MappingTarget BoychenkoCone updatedObject, BoychenkoConeDTO sourceDTO);

    @Mapping(target = "sample", source = "updateData")
    @Mapping(target = "undisturbedStrImmersionDepthFirstMeasur", expression = "java(0f)")
    @Mapping(target = "undisturbedStrImmersionDepthSecondMeasur", expression = "java(0f)")
    @Mapping(target = "undisturbedStrImmersionDepthThirdMeasur", expression = "java(0f)")
    @Mapping(target = "brokenStrImmersionDepthFirstMeasur", expression = "java(0f)")
    @Mapping(target = "brokenStrImmersionDepthSecondMeasur", expression = "java(0f)")
    @Mapping(target = "brokenStrImmersionDepthThirdMeasur", expression = "java(0f)")
    @Mapping(target = "undisturbedStrImmersionDepthAverage", expression = "java(0f)")
    @Mapping(target = "brokenStrImmersionDepthAverage", expression = "java(0f)")
    void updateBoychenkoConeFromSample(@MappingTarget BoychenkoCone updatedObject, Sample updateData);
}
