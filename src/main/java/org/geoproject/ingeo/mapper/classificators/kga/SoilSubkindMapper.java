package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindDto;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkind;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfiguration.class,
uses = {SoilKindGroupTypeMapper.class})
public interface SoilSubkindMapper {

    @Mapping(target = "soilKindGroupTypeDto", source = "soilSubkind.soilKindGroupType")
    SoilSubkindDto soilSubkindToSoilSubkindDto(SoilSubkind soilSubkind);

    List<SoilSubkindDto> soilSubkindToSoilSubkindDto(List<SoilSubkind> soilSubkinds);
}
