package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionResultDto;
import org.geoproject.ingeo.models.SoilCorrosionResult;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfiguration.class)
public interface SoilCorrosionResultMapper {

    public SoilCorrosionResult dtoToModel(SoilCorrosionResultDto dto);

    public SoilCorrosionResultDto modelToDto(SoilCorrosionResult model);
}
