package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshResultDto;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMesh;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
//public interface ConstructionMeshMapper extends IMapper<ConstructionMesh, ConstructionMeshResultDto> {
public interface GranCompositionConstructionMeshMapper {


    @Mapping(target = "surveyPointNumber", ignore = true)
    @Mapping(target = "depthMin", ignore = true)
    @Mapping(target = "depthMax", ignore = true)
    @Mapping(target = "laborNumber", source = "source.sample.laborNumber")
    ConstructionMeshResultDto granCompositionConstructionMeshToConstructionMeshResultDto(GranCompositionConstructionMesh source);

    List<ConstructionMeshResultDto> granCompositionConstructionMeshToConstructionMeshResultDto(List<GranCompositionConstructionMesh> source);

//    @Override
    default void updateGranCompositionConstructionMeshFromConstructionMeshResultDto(List<GranCompositionConstructionMesh> updatedObjects,
                                                                     List<ConstructionMeshResultDto> sourceDTOs) {
        for (var updatedObject : updatedObjects) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(updatedObject.getSample().getLaborNumber(), sourceDTO.getLaborNumber())) {
                    this.updateGranCompositionConstructionMeshFromConstructionMeshResultDto(updatedObject, sourceDTO);
                }
            }
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "laborNumber", ignore = true)
//    @Mapping(target = "totalSubsample", ignore = true)
//    @Mapping(target = "sieveDropperSubsample", ignore = true)
//    @Mapping(target = "potNumber", ignore = true)
//    @Mapping(target = "potMass", ignore = true)
//    @Mapping(target = "potMassWithSediment", ignore = true)
//    @Mapping(target = "mass_X_more_70", ignore = true)
//    @Mapping(target = "mass_X_70_40", ignore = true)
//    @Mapping(target = "mass_X_40_20", ignore = true)
//    @Mapping(target = "mass_X_20_10", ignore = true)
//    @Mapping(target = "mass_X_10_5", ignore = true)
//    @Mapping(target = "mass_X_5_25", ignore = true)
//    @Mapping(target = "mass_X_25_2", ignore = true)
//    @Mapping(target = "mass_X_2_125", ignore = true)
//    @Mapping(target = "mass_X_125_1", ignore = true)
//    @Mapping(target = "mass_X_1_063", ignore = true)
//    @Mapping(target = "mass_X_063_050", ignore = true)
//    @Mapping(target = "mass_X_050_0315", ignore = true)
//    @Mapping(target = "mass_X_0315_025", ignore = true)
//    @Mapping(target = "mass_X_025_016", ignore = true)
//    @Mapping(target = "mass_X_016_01", ignore = true)
//    @Mapping(target = "HCl", ignore = true)
    void updateGranCompositionConstructionMeshFromConstructionMeshResultDto(@MappingTarget GranCompositionConstructionMesh updatedObject, ConstructionMeshResultDto sourceDTO);

//    @Mapping(target = "laborNumber", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "totalSubsample", ignore = true)
//    @Mapping(target = "sieveDropperSubsample", ignore = true)
//    @Mapping(target = "potNumber", ignore = true)
//    @Mapping(target = "potMass", ignore = true)
//    @Mapping(target = "potMassWithSediment", ignore = true)
//    @Mapping(target = "mass_X_more_70", ignore = true)
//    @Mapping(target = "mass_X_70_40", ignore = true)
//    @Mapping(target = "mass_X_40_20", ignore = true)
//    @Mapping(target = "mass_X_20_10", ignore = true)
//    @Mapping(target = "mass_X_10_5", ignore = true)
//    @Mapping(target = "mass_X_5_25", ignore = true)
//    @Mapping(target = "mass_X_25_2", ignore = true)
//    @Mapping(target = "mass_X_2_125", ignore = true)
//    @Mapping(target = "mass_X_125_1", ignore = true)
//    @Mapping(target = "mass_X_1_063", ignore = true)
//    @Mapping(target = "mass_X_063_050", ignore = true)
//    @Mapping(target = "mass_X_050_0315", ignore = true)
//    @Mapping(target = "mass_X_0315_025", ignore = true)
//    @Mapping(target = "mass_X_025_016", ignore = true)
//    @Mapping(target = "mass_X_016_01", ignore = true)
//    @Mapping(target = "HCl", ignore = true)
//    @Mapping(target = "x_more_70", ignore = true)
//    @Mapping(target = "x_70_40", ignore = true)
//    @Mapping(target = "x_40_20", ignore = true)
//    @Mapping(target = "x_20_10", ignore = true)
//    @Mapping(target = "x_10_5", ignore = true)
//    @Mapping(target = "x_5_25", ignore = true)
//    @Mapping(target = "x_25_2", ignore = true)
//    @Mapping(target = "x_2_125", ignore = true)
//    @Mapping(target = "x_125_1", ignore = true)
//    @Mapping(target = "x_1_063", ignore = true)
//    @Mapping(target = "x_063_050", ignore = true)
//    @Mapping(target = "x_050_0315", ignore = true)
//    @Mapping(target = "x_0315_025", ignore = true)
//    @Mapping(target = "x_025_016", ignore = true)
//    @Mapping(target = "x_016_01", ignore = true)
//    @Mapping(target = "x_01_005", ignore = true)
//    @Mapping(target = "x_less_005", ignore = true)
//    @Mapping(target = "correction", ignore = true)
//    @Mapping(target = "soilKind", ignore = true)
//    @Mapping(target = "uniformityCoefficient", ignore = true)
//    @Mapping(target = "uniformityDegree", ignore = true)
//    @Mapping(target = "finenessModulus", ignore = true)
//    @Mapping(target = "sandGroupByFinenessModulus", ignore = true)
//    @Mapping(target = "lensesAndSeams", ignore = true)
//    void updateConstructionMeshFromSample(@MappingTarget ConstructionMesh updatedObject, Sample updateData);

//    @Override
//    ConstructionMesh updateTFromY(ConstructionMesh updatedObject, ConstructionMeshDTO sourceDTO);

    default void updateGranCompositionConstructionMesh(@MappingTarget GranCompositionConstructionMesh updatedGranCompositionConstructionMesh, Sample updateData) {
        updatedGranCompositionConstructionMesh.setSample(updateData);
    }
}