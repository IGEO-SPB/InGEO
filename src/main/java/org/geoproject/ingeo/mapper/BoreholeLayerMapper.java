package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDto;
import org.geoproject.ingeo.mapper.classificators.kga.ColorMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilClassKindGroupMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilClassMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilKindMapper;
import org.geoproject.ingeo.mapper.qualifier.EgeMapperQualifier;
import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class,
        uses = {EgeMapperQualifier.class, SoilKindMapper.class, SoilClassMapper.class,
                SoilClassKindGroupMapper.class, ColorMapper.class})
public interface BoreholeLayerMapper {

    @Mapping(target = "surveyPoint", ignore = true)
    @Mapping(target = "number", ignore = true)
//    @Mapping(target = "isEditableFromEgeListBasic", ignore = true)
    @Mapping(target = "isEditableFromEgeList", constant = "false")

    @Mapping(target = "ege", source = "dto.egeDto.id", qualifiedByName = {"EgeMapperQualifier", "getEgeById"})
    @Mapping(target = "hatching", source = "dto.hatchingDto.id", qualifiedByName = {"EgeMapperQualifier", "getHatchingById"})
    @Mapping(target = "firstLayerConsistency", source = "dto.firstLayerConsistencyDto.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyById"})
    @Mapping(target = "secondLayerConsistency", source = "dto.secondLayerConsistencyDto.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyById"})
    @Mapping(target = "color", source = "dto.colorDto.id", qualifiedByName = {"EgeMapperQualifier", "getColorById"})
    @Mapping(target = "credoColor", ignore = true)
    @Mapping(target = "hatchingCredo", ignore = true)
    @Mapping(target = "isArchive", constant = "false")

    @Mapping(target = "SS1", ignore = true)
    @Mapping(target = "SS2", ignore = true)
    @Mapping(target = "SS3", ignore = true)
    @Mapping(target = "SS4", ignore = true)
    @Mapping(target = "SS5", ignore = true)
    @Mapping(target = "SS6", ignore = true)
    @Mapping(target = "SS7", ignore = true)
    @Mapping(target = "SS8", ignore = true)
    @Mapping(target = "SS9", ignore = true)
    @Mapping(target = "SS10", ignore = true)
    @Mapping(target = "SS11", ignore = true)
    @Mapping(target = "SS12", ignore = true)
    @Mapping(target = "SS13", ignore = true)
    @Mapping(target = "SS14", ignore = true)
    @Mapping(target = "SS15", ignore = true)
    @Mapping(target = "SS16", ignore=true)
    @Mapping(target = "SS17", ignore=true)
    @Mapping(target = "SS18", ignore=true)
    @Mapping(target = "SS19", ignore=true)
    @Mapping(target = "SS20", ignore=true)

    @Mapping(target = "SSA1", ignore=true)
    @Mapping(target = "SSA2", ignore=true)
    @Mapping(target = "SSA3", ignore=true)
    @Mapping(target = "SSA4", ignore = true)
    @Mapping(target = "SSA5", ignore = true)
    @Mapping(target = "SSA6", ignore = true)
    @Mapping(target = "SSA7", ignore = true)
    @Mapping(target = "SSA8", ignore = true)
    @Mapping(target = "SSA9", ignore = true)
    @Mapping(target = "SSA10", ignore = true)
    @Mapping(target = "SSA11", ignore = true)
    @Mapping(target = "SSA12", ignore = true)

    @Mapping(target = "soilClass", ignore = true)
    @Mapping(target = "soilKind", ignore = true)
    @Mapping(target = "soilClassKindGroup", ignore = true)
    @Mapping(target = "waterDepth", ignore = true)
    BoreholeLayer boreholeLayerDtoToBoreholeLayer(BoreholeLayerDto dto);

    List<BoreholeLayer> boreholeLayerDtoToBoreholeLayer(List<BoreholeLayerDto> sourceDtos);


    @Mapping(target = "surveyPointId", source = "boreholeLayer.surveyPoint.id")
    @Mapping(target = "shortName", source = "boreholeLayer.ege.shortName")

    @Mapping(target = "isEditableFromEgeListBasic", source = "boreholeLayer.isEditableFromEgeList")
    @Mapping(target = "isEditableFromEgeList", ignore = true)

    @Mapping(target = "egeDto", source = "boreholeLayer.ege.id", qualifiedByName = {"EgeMapperQualifier", "getEgeDtoById"})
    @Mapping(target = "hatchingDto", source = "boreholeLayer.hatching.id", qualifiedByName = {"EgeMapperQualifier", "getHatchingDtoById"})
    @Mapping(target = "firstLayerConsistencyDto", source = "boreholeLayer.firstLayerConsistency.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyDtoById"})
    @Mapping(target = "secondLayerConsistencyDto", source = "boreholeLayer.secondLayerConsistency.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyDtoById"})
    @Mapping(target = "colorDto", source = "boreholeLayer.color.id", qualifiedByName = {"EgeMapperQualifier", "getColorDtoById"})
    BoreholeLayerDto boreholeLayerToBoreholeLayerDto(BoreholeLayer boreholeLayer);

    List<BoreholeLayerDto> boreholeLayerToBoreholeLayerDto(List<BoreholeLayer> boreholeLayers);

    @Mapping(target = "number", ignore = true)
    @Mapping(target = "surveyPoint", ignore = true)
    @Mapping(target = "credoColor", ignore = true)
    @Mapping(target = "hatchingCredo", ignore = true)
    @Mapping(target = "isArchive", ignore = true)
    @Mapping(target = "isEditableFromEgeList", source = "sourceDto.isEditableFromEgeListBasic")
    @Mapping(target = "ege", source = "sourceDto.egeDto.id", qualifiedByName = {"EgeMapperQualifier", "getEgeById"})
    @Mapping(target = "hatching", source = "sourceDto.hatchingDto.id", qualifiedByName = {"EgeMapperQualifier", "getHatchingById"})
    @Mapping(target = "firstLayerConsistency", source = "sourceDto.firstLayerConsistencyDto.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyById"})
    @Mapping(target = "secondLayerConsistency", source = "sourceDto.secondLayerConsistencyDto.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyById"})
    @Mapping(target = "color", source = "sourceDto.colorDto.id", qualifiedByName = {"EgeMapperQualifier", "getColorById"})

    @Mapping(target = "SS1", ignore = true)
    @Mapping(target = "SS2", ignore = true)
    @Mapping(target = "SS3", ignore = true)
    @Mapping(target = "SS4", ignore = true)
    @Mapping(target = "SS5", ignore = true)
    @Mapping(target = "SS6", ignore = true)
    @Mapping(target = "SS7", ignore = true)
    @Mapping(target = "SS8", ignore = true)
    @Mapping(target = "SS9", ignore = true)
    @Mapping(target = "SS10", ignore = true)
    @Mapping(target = "SS11", ignore = true)
    @Mapping(target = "SS12", ignore = true)
    @Mapping(target = "SS13", ignore = true)
    @Mapping(target = "SS14", ignore = true)
    @Mapping(target = "SS15", ignore = true)
    @Mapping(target = "SS16", ignore=true)
    @Mapping(target = "SS17", ignore=true)
    @Mapping(target = "SS18", ignore=true)
    @Mapping(target = "SS19", ignore=true)
    @Mapping(target = "SS20", ignore=true)

    @Mapping(target = "SSA1", ignore=true)
    @Mapping(target = "SSA2", ignore=true)
    @Mapping(target = "SSA3", ignore=true)
    @Mapping(target = "SSA4", ignore = true)
    @Mapping(target = "SSA5", ignore = true)
    @Mapping(target = "SSA6", ignore = true)
    @Mapping(target = "SSA7", ignore = true)
    @Mapping(target = "SSA8", ignore = true)
    @Mapping(target = "SSA9", ignore = true)
    @Mapping(target = "SSA10", ignore = true)
    @Mapping(target = "SSA11", ignore = true)
    @Mapping(target = "SSA12", ignore = true)

    @Mapping(target = "soilClass", ignore = true)
    @Mapping(target = "soilKind", ignore = true)
    @Mapping(target = "soilClassKindGroup", ignore = true)
    @Mapping(target = "waterDepth", ignore = true)
    void updateBoreholeLayerFromBoreholeLayerDto(@MappingTarget BoreholeLayer boreholeLayer, BoreholeLayerDto sourceDto);

    @AfterMapping
    default BoreholeLayer afterMapping(@MappingTarget BoreholeLayer boreholeLayer) {

        if (Objects.nonNull(boreholeLayer)) {
            if (Objects.isNull(boreholeLayer.getEge()) || Objects.isNull(boreholeLayer.getEge().getId())) {
                boreholeLayer.setEge(null);
            }

            if (Objects.isNull(boreholeLayer.getColor()) || Objects.isNull(boreholeLayer.getColor().getId())) {
                boreholeLayer.setColor(null);
            }

            if (Objects.isNull(boreholeLayer.getFirstLayerConsistency()) || Objects.isNull(boreholeLayer.getFirstLayerConsistency().getId())) {
                boreholeLayer.setFirstLayerConsistency(null);
            }

            if (Objects.isNull(boreholeLayer.getSecondLayerConsistency()) || Objects.isNull(boreholeLayer.getSecondLayerConsistency().getId())) {
                boreholeLayer.setSecondLayerConsistency(null);
            }

            if (Objects.isNull(boreholeLayer.getHatching()) || Objects.isNull(boreholeLayer.getHatching().getId())) {
                boreholeLayer.setHatching(null);
            }
        }

        return boreholeLayer;
    }

    default void updateBoreholeLayerFromBoreholeLayerDto(List<BoreholeLayer> boreholeLayers, List<BoreholeLayerDto> sourceDtos) {
        boreholeLayers.forEach(boreholeLayer -> {
            var sourceDto = sourceDtos.stream()
                    .filter(dto -> Objects.equals(boreholeLayer.getId(), dto.getId()))
                    .findFirst()
                    .orElse(null);

            updateBoreholeLayerFromBoreholeLayerDto(boreholeLayer, sourceDto);
        });
    }

    @Mapping(target = "id", ignore = true)
    BoreholeLayerDto cloneEgeDto(BoreholeLayerDto egeDto);

    @Mapping(target = "boreholeLayerId", source = "boreholeLayer.id")

    @Mapping(target = "shortName", source = "boreholeLayer.ege.shortName")
//    @Mapping(target = "soilClass", source = "boreholeLayer.soilClass")
    @Mapping(target = "soilClassDto", source = "boreholeLayer.soilClass")
    @Mapping(target = "soilClassKindGroupDto", source = "boreholeLayer.soilClassKindGroup")
//    @Mapping(target = "soilKind", source = "boreholeLayer.soilKind")
    @Mapping(target = "waterDepth", source = "boreholeLayer.waterDepth")

    @Mapping(target = "soilKindDto", source = "boreholeLayer.soilKind")
    @Mapping(target = "colorDto", source = "boreholeLayer.color")

    @Mapping(target = "egeId", ignore = true)
    @Mapping(target = "soilSubkindMap", ignore = true)
    @Mapping(target = "soilSubkindAdjMap", ignore = true)
    DescriptionKgaDto boreholeLayerToDescriptionKgaDto(BoreholeLayer boreholeLayer);

    default void updateBoreholeLayer(@MappingTarget BoreholeLayer boreholeLayer, DescriptionKgaDto descriptionKgaDto) {
        boreholeLayer.setDescriptionKga(descriptionKgaDto.getDescriptionKga());

        Class<? extends BoreholeLayer> clazz = boreholeLayer.getClass();

        descriptionKgaDto.getSoilSubkindMap().forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(boreholeLayer, value.getId());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(boreholeLayer, null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//        boreholeLayer.setColor(descriptionKgaDto.getColor());

        descriptionKgaDto.getSoilSubkindAdjMap().forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(boreholeLayer, value.getId());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(boreholeLayer, null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        boreholeLayer.setWaterDepth(descriptionKgaDto.getWaterDepth());
        boreholeLayer.setDescriptionKga(descriptionKgaDto.getDescriptionKga());
    }
}
