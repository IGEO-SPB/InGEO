package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.mapper.classificators.kga.ColorMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilClassKindGroupMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilClassMapper;
import org.geoproject.ingeo.mapper.classificators.kga.SoilKindMapper;
import org.geoproject.ingeo.mapper.qualifier.EgeMapperQualifier;
import org.geoproject.ingeo.models.Ege;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class,
        uses = {EgeMapperQualifier.class,
                SoilClassMapper.class,
                SoilClassKindGroupMapper.class,
                SoilKindMapper.class,
                ColorMapper.class
        }
)
public interface EgeMapper {

    @Mapping(target = "genesisDto", source = "ege.genesis.id", qualifiedByName = {"EgeMapperQualifier", "getGenesisDtoById"})
    @Mapping(target = "hatchingDto", source = "ege.hatching.id", qualifiedByName = {"EgeMapperQualifier", "getHatchingDtoById"})
    @Mapping(target = "consistencyDto", source = "ege.consistency.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyDtoById"})
    EgeDto egeToEgeDto(Ege ege);

    List<EgeDto> egeToEgeDto(List<Ege> ege);

//    @Mapping(target = "SS1", source = "ege.SS1", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS2", source = "ege.SS2", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS3", source = "ege.SS3", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS4", source = "ege.SS4", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS5", source = "ege.SS5", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS6", source = "ege.SS6", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS7", source = "ege.SS7", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS8", source = "ege.SS8", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS9", source = "ege.SS9", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS10", source = "ege.SS10", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS11", source = "ege.SS11", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS12", source = "ege.SS12", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS13", source = "ege.SS13", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS14", source = "ege.SS14", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS15", source = "ege.SS15", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS16", source = "ege.SS16", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS17", source = "ege.SS17", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS18", source = "ege.SS18", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS19", source = "ege.SS19", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//    @Mapping(target = "SS20", source = "ege.SS20", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindById"})
//
//    @Mapping(target = "ssa1", source = "ege.ssa1", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa2", source = "ege.ssa2", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa3", source = "ege.ssa3", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa4", source = "ege.ssa4", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa5", source = "ege.ssa5", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa6", source = "ege.ssa6", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa7", source = "ege.ssa7", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa8", source = "ege.ssa8", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa9", source = "ege.ssa9", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa10", source = "ege.ssa10", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa11", source = "ege.ssa11", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})
//    @Mapping(target = "ssa12", source = "ege.ssa12", qualifiedByName = {"EgeMapperQualifier", "getSoilSubkindAdjById"})

    @Mapping(target = "egeId", source = "ege.id")
    @Mapping(target = "boreholeLayerId", ignore = true)
    @Mapping(target = "soilSubkindMap", ignore = true)
    @Mapping(target = "soilSubkindAdjMap", ignore = true)

    @Mapping(target = "soilClassDto", source = "ege.soilClass")
    @Mapping(target = "soilClassKindGroupDto", source = "ege.soilClassKindGroup")
    @Mapping(target = "soilKindDto", source = "ege.soilKind")
    @Mapping(target = "colorDto", source = "ege.color")
    DescriptionKgaDto egeToDescriptionKgaDto(Ege ege);

    default void updateEge(@MappingTarget Ege ege, DescriptionKgaDto descriptionKgaDto) {
//        ege.set

//        Mappers.getMapper(SoilClassMapper.class).soilClassDtoToSoilClass(descriptionKgaDto.getSoilClassDto());

//        ege.setSoilClass(descriptionKgaDto.getSoilClass());
//        ege.setSoilClassKindGroup(descriptionKgaDto.getSoilClassKindGroup());
//        ege.setSoilKind(descriptionKgaDto.getSoilKind());

        ege.setDescriptionKga(descriptionKgaDto.getDescriptionKga());

//        ege.setSS1(descriptionKgaDto.getSoilSubkindMap().get("SS1").getId());

        Class<? extends Ege> clazz = ege.getClass();

        descriptionKgaDto.getSoilSubkindMap().forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(ege, value.getId());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(ege, null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });


//    private SoilSubkind SS1;
//    private SoilSubkind SS2;
//    private SoilSubkind SS3;
//    private SoilSubkind SS4;
//    private SoilSubkind SS5;
//    private SoilSubkind SS6;
//    private SoilSubkind SS7;
//    private SoilSubkind SS8;
//    private SoilSubkind SS9;
//    private SoilSubkind SS10;
//    private SoilSubkind SS11;
//    private SoilSubkind SS12;
//    private SoilSubkind SS13;
//    private SoilSubkind SS14;
//    private SoilSubkind SS15;
//    private SoilSubkind SS16;
//    private SoilSubkind SS17;
//    private SoilSubkind SS18;
//    private SoilSubkind SS19;
//    private SoilSubkind SS20;


//        ege.setColor(descriptionKgaDto.getColor());

        descriptionKgaDto.getSoilSubkindAdjMap().forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(ege, value.getId());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(ege, null);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//    private SoilSubkindAdj ssa1;
//    private SoilSubkindAdj ssa2;
//    private SoilSubkindAdj ssa3;
//    private SoilSubkindAdj ssa4;
//    private SoilSubkindAdj ssa5;
//    private SoilSubkindAdj ssa6;
//    private SoilSubkindAdj ssa7;
//    private SoilSubkindAdj ssa8;
//    private SoilSubkindAdj ssa9;
//    private SoilSubkindAdj ssa10;
//    private SoilSubkindAdj ssa11;
//    private SoilSubkindAdj ssa12;

        ege.setWaterDepth(descriptionKgaDto.getWaterDepth());
        ege.setDescriptionKga(descriptionKgaDto.getDescriptionKga());
    }


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

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "code", ignore = true)

    @Mapping(target = "genesis", source = "dto.genesisDto.id", qualifiedByName = {"EgeMapperQualifier", "getGenesisById"})
    @Mapping(target = "hatching", source = "dto.hatchingDto.id", qualifiedByName = {"EgeMapperQualifier", "getHatchingById"})
    @Mapping(target = "consistency", source = "dto.consistencyDto.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyById"})

    @Mapping(target = "soilClass", ignore = true)
    @Mapping(target = "soilKind", ignore = true)
    @Mapping(target = "soilClassKindGroup", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "GB_NMB", ignore = true)
    @Mapping(target = "f_Opis", ignore = true)
    @Mapping(target = "waterDepth", ignore = true)
    @Mapping(target = "credoColor", ignore = true)
    @Mapping(target = "hatchingCredo", ignore = true)
    @Mapping(target = "isArchive", constant = "false")
    @Mapping(target = "boreholeLayerList", ignore = true)
    Ege egeDtoToEge(EgeDto dto);

    List<Ege> egeDtoToEge(List<EgeDto> dtos);

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

    @Mapping(target = "project", ignore = true)
    @Mapping(target = "code", ignore = true)

    @Mapping(target = "genesis", source = "sourceDto.genesisDto.id", qualifiedByName = {"EgeMapperQualifier", "getGenesisById"})
    @Mapping(target = "hatching", source = "sourceDto.hatchingDto.id", qualifiedByName = {"EgeMapperQualifier", "getHatchingById"})
    @Mapping(target = "consistency", source = "sourceDto.consistencyDto.id", qualifiedByName = {"EgeMapperQualifier", "getConsistencyById"})

    @Mapping(target = "soilClass", ignore = true)
    @Mapping(target = "soilKind", ignore = true)
    @Mapping(target = "soilClassKindGroup", ignore = true)
    @Mapping(target = "color", ignore = true)
    @Mapping(target = "GB_NMB", ignore = true)
    @Mapping(target = "f_Opis", ignore = true)
    @Mapping(target = "waterDepth", ignore = true)
    @Mapping(target = "credoColor", ignore = true)
    @Mapping(target = "hatchingCredo", ignore = true)
    @Mapping(target = "isArchive", constant = "false")
    @Mapping(target = "boreholeLayerList", ignore = true)
    void updateEgeFromEgeDto(@MappingTarget Ege ege, EgeDto sourceDto);

    @AfterMapping
    default Ege afterMapping(@MappingTarget Ege ege) {

        if (Objects.nonNull(ege)) {
            if (Objects.isNull(ege.getConsistency()) || Objects.isNull(ege.getConsistency().getId())) {
                ege.setConsistency(null);
            }

            if (Objects.isNull(ege.getGenesis()) || Objects.isNull(ege.getGenesis().getId())) {
                ege.setGenesis(null);
            }

            if (Objects.isNull(ege.getHatching()) || Objects.isNull(ege.getHatching().getId())) {
                ege.setHatching(null);
            }
        }

        return ege;
    }

    default void updateEgeFromEgeDto(@MappingTarget List<Ege> egeList, List<EgeDto> sourceDtos) {
        egeList.forEach(ege -> {
            var sourceDto = sourceDtos.stream()
                    .filter(dto -> Objects.equals(ege.getId(), dto.getId()))
                    .findFirst()
                    .orElse(null);

            updateEgeFromEgeDto(ege, sourceDto);
        });
    }

    @Mapping(target = "id", ignore = true)
    EgeDto cloneEgeDto(EgeDto sourceDto);
}
