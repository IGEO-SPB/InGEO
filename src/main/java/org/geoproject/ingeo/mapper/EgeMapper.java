package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.DescriptionKgaDto;
import org.geoproject.ingeo.dto.SurveyPointDTO;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.mapper.qualifier.EgeMapperQualifier;
import org.geoproject.ingeo.mapper.qualifier.ShearMapperQualifier;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.SurveyPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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

    @Mapping(target = "soilSubkindMap", ignore = true)
    @Mapping(target = "soilSubkindAdjMap", ignore = true)
    DescriptionKgaDto egeToDescriptionKgaDto(Ege ege);
}
