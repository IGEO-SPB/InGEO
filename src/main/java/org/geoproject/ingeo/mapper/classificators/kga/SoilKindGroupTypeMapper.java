package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.kga.SoilKindGroupTypeDto;
import org.geoproject.ingeo.models.classificators.kga.SoilKindGroupType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfiguration.class,
uses = {SoilKindMapper.class, SoilGroupTypeMapper.class})
public interface SoilKindGroupTypeMapper {

    @Mapping(target = "soilGroupTypeDto", source = "soilKindGroupType.soilGroupType")
    @Mapping(target = "soilKindDto", source = "soilKindGroupType.soilKind")
    SoilKindGroupTypeDto soilKindGroupTypeToSoilKindGroupTypeDto(SoilKindGroupType soilKindGroupType);

    List<SoilKindGroupTypeDto> soilKindGroupTypeToSoilKindGroupTypeDto(List<SoilKindGroupType> soilKindGroupTypes);
}
