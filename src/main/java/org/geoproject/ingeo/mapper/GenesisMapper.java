package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.GenesisDto;
import org.geoproject.ingeo.models.classificators.Genesis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface GenesisMapper {

    @Mapping(target = "genesisCode", source = "genesis.codeUni")
    @Mapping(target = "genesisDescription", source = "genesis.name")
    GenesisDto genesisToGenesisDto(Genesis genesis);

    List<GenesisDto> genesisToGenesisDto(List<Genesis> genesisList);
}
