package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionResultDto;
import org.geoproject.ingeo.models.labor.SoilCorrosionResult;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfiguration.class)
public interface SoilCorrosionResultMapper {

    public SoilCorrosionResult dtoToModel(SoilCorrosionResultDto dto);

    public SoilCorrosionResultDto modelToDto(SoilCorrosionResult model);
}
