package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.SoilCorrosionInputDto;
import org.geoproject.ingeo.models.labor.SoilCorrosionInput;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfiguration.class)
public interface SoilCorrosionInputMapper {

    public SoilCorrosionInput dtoToModel(SoilCorrosionInputDto dto);

    public SoilCorrosionInputDto modelToDto(SoilCorrosionInput model);
}
