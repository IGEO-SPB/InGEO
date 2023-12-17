package org.geoproject.ingeo.mapper.classificators;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.HatchingDto;
import org.geoproject.ingeo.models.classificators.Hatching;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface HatchingMapper {

    HatchingDto hatchingToHatchingDto(Hatching hatchingList);

    List<HatchingDto> hatchingToHatchingDto(List<Hatching> hatchingList);
}
