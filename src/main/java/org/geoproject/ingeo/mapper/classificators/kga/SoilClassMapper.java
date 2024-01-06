package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.kga.SoilClassDto;
import org.geoproject.ingeo.models.classificators.kga.SoilClass;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface SoilClassMapper {

    SoilClassDto soilClassToSoilClassDto(SoilClass soilClass);

    List<SoilClassDto> soilClassToSoilClassDto(List<SoilClass> soilClasses);
}
