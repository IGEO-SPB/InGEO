package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.kga.SoilGroupTypeDto;
import org.geoproject.ingeo.models.classificators.kga.SoilGroupType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface SoilGroupTypeMapper {

    SoilGroupTypeDto soilGroupTypeToSoilGroupTypeDto(SoilGroupType soilGroupType);

    List<SoilGroupTypeDto> soilGroupTypeToSoilGroupTypeDto(List<SoilGroupType> soilGroupTypes);
}
