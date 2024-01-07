package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.kga.SoilClassDto;
import org.geoproject.ingeo.dto.classificators.kga.SoilClassKindGroupDto;
import org.geoproject.ingeo.models.classificators.kga.SoilClassKindGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfiguration.class,
uses = {SoilClassMapper.class})
public interface SoilClassKindGroupMapper {

    @Mapping(target = "soilClassDto", source = "soilClassKindGroup.soilClass")
    SoilClassKindGroupDto soilClassKindGroupToSoilClassKindGroupDto(SoilClassKindGroup soilClassKindGroup);

    List<SoilClassKindGroupDto> soilClassKindGroupToSoilClassKindGroupDto(List<SoilClassKindGroup> soilClassKindGroups);
}
