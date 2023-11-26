package org.geoproject.ingeo.mapper.laborMethods;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.methodDtos.CompressionDto;
import org.geoproject.ingeo.models.labor.Compression;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfiguration.class)
public interface CompressionMapper {

    Compression.CompressionItem itemDtoToModel(CompressionDto.CompressionItemDto dto);


    CompressionDto.CompressionItemDto itemModelToDto(Compression.CompressionItem model);


    @Mapping(target = "ring", ignore = true)
    Compression dtoToModel(CompressionDto dto);

    @Mapping(target = "ring", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sample", ignore = true)
    void updateModel(@MappingTarget Compression model, CompressionDto dto);

    @Mapping(target = "ringNumber", ignore = true)
    @Mapping(target = "ringHeight", ignore = true)
    CompressionDto modelToDto(Compression model);
}
