package org.geoproject.ingeo.mapper.classificators;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.classificators.ConsistencyDto;
import org.geoproject.ingeo.models.classificators.Consistency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface ConsistencyMapper {

    ConsistencyDto consistencyToConsistencyDto(Consistency consistency);

    List<ConsistencyDto> consistencyToConsistencyDto(List<Consistency> consistencyList);
}
