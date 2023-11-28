package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.SurveyPointDTO;
import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.SurveyPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfiguration.class)
public interface EgeMapper {

    @Mapping(target = "genesisCode", source = "ege.genesis.code")
    @Mapping(target = "genesisDescription", source = "ege.genesis.name")
    EgeDto egeToEgeDto(Ege ege);
}
