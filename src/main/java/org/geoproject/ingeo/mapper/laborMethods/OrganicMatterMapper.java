package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.models.labor.OrganicMatter;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface OrganicMatterMapper {

    @Mapping(target = "laborNumber", source = "source.sample.laborNumber")
    OrganicMatterDTO organicMatterToOrganicMatterDTO(OrganicMatter source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    default void updateOrganicMatter(@MappingTarget List<OrganicMatter> updatedObjects, List<OrganicMatterDTO> sourceDTOs) {
        for (var object : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(object.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updateOrganicMatter(object, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateOrganicMatter(@MappingTarget OrganicMatter updatedObject, OrganicMatterDTO sourceDTO);

    @Mapping(target = "sample", source = "updateData")
    @Mapping(target = "emptyPotMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "emptyPotMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "absolutelyDrySoilPotMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "absolutelyDrySoilPotMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "calcinedSoilPotMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "calcinedSoilPotMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "ignitionLossMassFirstMeasurement", expression = "java(0f)")
    @Mapping(target = "ignitionLossMassSecondMeasurement", expression = "java(0f)")
    @Mapping(target = "dryMatterContentBefore", expression = "java(0f)")
    @Mapping(target = "dryMatterContentAfter", expression = "java(0f)")
    @Mapping(target = "ignitionLossAverageMass", expression = "java(0f)")
    @Mapping(target = "ashContent", expression = "java(0f)")
    @Mapping(target = "p250", expression = "java(0f)")
    @Mapping(target = "decompositionDegree", expression = "java(0f)")
    void updateOrganicMatterFromSample(@MappingTarget OrganicMatter updatedObject, Sample updateData);
}
