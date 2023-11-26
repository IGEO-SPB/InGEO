package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.BoychenkoConeDTO;
import org.geoproject.ingeo.dto.methodDtos.DensityDTO;
import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.dto.methodDtos.PhysicalPropertiesDTO;
import org.geoproject.ingeo.dto.methodDtos.RingDensityDTO;
import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.models.PhysicalProperties;
import org.geoproject.ingeo.models.Sample;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class)
public interface PhysicalPropertiesMapper {

    @Mapping(target = "surveyPointNumber", ignore = true)
    @Mapping(target = "depthMin", ignore = true)
    @Mapping(target = "depthMax", ignore = true)
    @Mapping(target = "sum_2_005", ignore = true)
    @Mapping(target = "sum_005_0002_old_005_0005", ignore = true)
    @Mapping(target = "sum_less_0002_old_less_0005", ignore = true)
    @Mapping(target = "sand", ignore = true)
    PhysicalPropertiesDTO physicalPropertiesToPhysicalPropertiesDTO(PhysicalProperties source);

    List<PhysicalPropertiesDTO> physicalPropertiesToPhysicalPropertiesDTO(List<PhysicalProperties> source);

    default void updatePhysicalPropertiesFromDtos(
            PhysicalProperties updatedPhysicalProperties,
            DensityDTO densityDTO,
            WaterContentDTO waterContentDTO,
            BoychenkoConeDTO boychenkoConeDTO,
            RingDensityDTO ringDensityDTO,
            OrganicMatterDTO organicMatterDTO
    ) {
        updatedPhysicalProperties.setAverageDensity(densityDTO.getAverageDensity());
        updatedPhysicalProperties.setRingDensityAverage(ringDensityDTO.getRingDensityAverage());
        updatedPhysicalProperties.setRingDrySoilDensity(ringDensityDTO.getRingDrySoilDensity());
        updatedPhysicalProperties.setVoidRatio(ringDensityDTO.getVoidRatio());
        updatedPhysicalProperties.setNaturalAverageWaterContent(waterContentDTO.getNaturalAverageWaterContent());
        updatedPhysicalProperties.setSaturationRatio(ringDensityDTO.getSaturationRatio());
        updatedPhysicalProperties.setFullWaterContent(ringDensityDTO.getFullWaterContent());
        updatedPhysicalProperties.setAverageLiquidLimit(waterContentDTO.getAverageLiquidLimit());
        updatedPhysicalProperties.setPlasticLimit(waterContentDTO.getAveragePlasticLimit());
        updatedPhysicalProperties.setPlasticityIndex(waterContentDTO.getPlasticityIndex());
        updatedPhysicalProperties.setLiquidityIndex(waterContentDTO.getLiquidityIndex());
        updatedPhysicalProperties.setUndisturbedStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getUndisturbedStrImmersionDepthAverage());
        updatedPhysicalProperties.setBrokenStrBoychenkoConeImmersionDepthAverage(boychenkoConeDTO.getBrokenStrImmersionDepthAverage());
        updatedPhysicalProperties.setIgnitionLossAverageMass(organicMatterDTO.getIgnitionLossAverageMass());
        updatedPhysicalProperties.setDecompositionDegree(organicMatterDTO.getDecompositionDegree());

    }

    default void updatePhysicalPropertiesFromSample(PhysicalProperties updatedPhysicalProperties,
                                                    Sample updateData,
                                                    DensityDTO densityDTO,
                                                    WaterContentDTO waterContentDTO,
                                                    BoychenkoConeDTO boychenkoConeDTO,
                                                    RingDensityDTO ringDensityDTO,
                                                    OrganicMatterDTO organicMatterDTO
                                                    ) {
        updatedPhysicalProperties.setSample(updateData);
        updatedPhysicalProperties.setLaborNumber(updateData.getLaborNumber());

        updatePhysicalPropertiesFromDtos(updatedPhysicalProperties,
                densityDTO,
                waterContentDTO,
                boychenkoConeDTO,
                ringDensityDTO,
                organicMatterDTO
                );
//        updatedPhysicalProperties.setAverageDensity(updateData.getAverageDensity());
//        updatedPhysicalProperties.setRingDensityAverage(updateData.getRingDensityAverage());
//        updatedPhysicalProperties.setRingDrySoilDensity(updateData.getRingDrySoilDensity());
//        updatedPhysicalProperties.setVoidRatio(updateData.getVoidRatio());
//        updatedPhysicalProperties.setNaturalAverageWaterContent(updateData.());
//        updatedPhysicalProperties.setSaturationRatio(updateData.getSaturationRatio());
//        updatedPhysicalProperties.setFullWaterContent(updateData.getFullWaterContent());
//        updatedPhysicalProperties.setAverageLiquidLimit(updateData.getLiquidityAverageWaterContent());
//        updatedPhysicalProperties.setAveragePlasticLimit(updateData.getPlasticAverageWaterContent());
//        updatedPhysicalProperties.setPlasticityIndex(updateData.getPlasticityIndex());
//        updatedPhysicalProperties.setLiquidityIndex(updateData.getLiquidityIndex());
//        updatedPhysicalProperties.setUndisturbedStrBoychenkoConeImmersionDepthAverage(updateData.getUndisturbedStrBoychenkoConeImmersionDepthAverage());
//        updatedPhysicalProperties.setBrokenStrBoychenkoConeImmersionDepthAverage(updateData.getBrokenStrBoychenkoConeImmersionDepthAverage());
//        updatedPhysicalProperties.setIgnitionLossAverageMass(updateData.getIgnitionLossAverageMass());
//        updatedPhysicalProperties.setDecompositionDegree(updateData.getDecompositionDegree());
    }

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "sample", ignore = true)
//    @Mapping(target = "laborNumber", ignore = true)
//    void updateTFromY(@MappingTarget PhysicalProperties physicalProperties,
//                                    PhysicalPropertiesDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    @Mapping(target = "laborNumber", ignore = true)
    void updatePhysicalProperties(@MappingTarget PhysicalProperties updatedObject, PhysicalPropertiesDTO sourceDTO);

    default void updatePhysicalProperties(List<PhysicalProperties> physicalProperties, List<PhysicalPropertiesDTO> sourceDTOs) {
        for (var sample : physicalProperties) {
            for (var sourceDTO : sourceDTOs) {
                if (Objects.equals(sample.getLaborNumber(), sourceDTO.getLaborNumber())) {
                    updatePhysicalProperties(sample, sourceDTO);
                }
            }
        }
    }

//    @Override
//    void updateTFromSample(PhysicalProperties updatedObject, Sample updateData);

//    @Override
//    PhysicalProperties updateTFromY(PhysicalProperties updatedObject, PhysicalPropertiesDTO sourceDTO);
}