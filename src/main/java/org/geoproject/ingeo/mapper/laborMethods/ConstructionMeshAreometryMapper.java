package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshAreometryDto;
import org.geoproject.ingeo.models.ConstructionMeshAreometry;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface ConstructionMeshAreometryMapper {

    @Mapping(target = "isSand", ignore = true)
    @Mapping(target = "surveyPointNumber", ignore = true)
    @Mapping(target = "depthMin", ignore = true)
    @Mapping(target = "depthMax", ignore = true)
    ConstructionMeshAreometryDto modelToDTO(ConstructionMeshAreometry source);

    List<ConstructionMeshAreometryDto> modelsToDTOs(List<ConstructionMeshAreometry> source);

    //    @Override
    default void updateModelsFromDTOs(List<ConstructionMeshAreometry> updatedObjects,
                                      List<ConstructionMeshAreometryDto> sourceDTOs) {
        for (var sample : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(sample.getLaborNumber(), sourceDTO.getLaborNumber())) {
                    this.updateModelFromDTO(sample, sourceDTO);
                }
            }
        }
    }


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateModelFromDTO(@MappingTarget ConstructionMeshAreometry updatedObject, ConstructionMeshAreometryDto sourceDTO);

    default ConstructionMeshAreometry updateConstructionMeshAreometry(@MappingTarget ConstructionMeshAreometry updatedConstructionMeshAreometry, Sample updateData) {
        updatedConstructionMeshAreometry.setSample(updateData);
        updatedConstructionMeshAreometry.setLaborNumber(updateData.getLaborNumber());

        return updatedConstructionMeshAreometry;
    }
}