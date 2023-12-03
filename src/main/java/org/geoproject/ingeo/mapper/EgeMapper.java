package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.SurveyPointDTO;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.mapper.qualifier.EgeMapperQualifier;
import org.geoproject.ingeo.mapper.qualifier.ShearMapperQualifier;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.geoproject.ingeo.models.classificators.kga.SoilKind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Map;
import java.util.Objects;

@Mapper(config = MapStructConfiguration.class,
        uses = {EgeMapperQualifier.class}
)
public interface EgeMapper {

    @Mapping(target = "genesisCode", source = "ege.genesis.code")
    @Mapping(target = "genesisDescription", source = "ege.genesis.name")
    EgeDto egeToEgeDto(Ege ege);

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
    @Mapping(target = "soilSubkindMap", ignore = true)
    @Mapping(target = "soilSubkindAdjMap", ignore = true)
    DescriptionKgaDto egeToDescriptionKgaDto(Ege ege);

    default void updateEge(@MappingTarget Ege ege, DescriptionKgaDto descriptionKgaDto) {
//        ege.set

        ege.setSoilClass(descriptionKgaDto.getSoilClass());
        ege.setSoilClassKindGroup(descriptionKgaDto.getSoilClassKindGroup());
        ege.setSoilKind(descriptionKgaDto.getSoilKind());

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


        ege.setColor(descriptionKgaDto.getColor());

        descriptionKgaDto.getSoilSubkindAdjMap().forEach((key, value) -> {
            if (Objects.nonNull(value)) {
                try {
                    var field = clazz.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(ege, value.getId());
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
    }

}
