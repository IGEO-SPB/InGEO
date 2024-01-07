package org.geoproject.ingeo.mapper.classificators.kga;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.kga.SoilSubkindAdjDto;
import org.geoproject.ingeo.models.classificators.kga.SoilSubkindAdj;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface SoilSubkindAdjMapper {

    SoilSubkindAdjDto soilSubkindAdjToSoilSubkindAdjDto(SoilSubkindAdj soilSubkindAdj);

    List<SoilSubkindAdjDto> soilSubkindAdjToSoilSubkindAdjDto(List<SoilSubkindAdj> soilSubkindAdjList);
}
