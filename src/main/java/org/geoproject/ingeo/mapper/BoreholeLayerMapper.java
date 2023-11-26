package com.geoproject.igeo.mapper;

import com.geoproject.igeo.config.MapStructConfiguration;
import com.geoproject.igeo.dto.mainViewsDto.BoreholeLayerDTO;
import com.geoproject.igeo.models.BoreholeLayer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfiguration.class)
public interface BoreholeLayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "surveyPoint", ignore = true)
    @Mapping(target = "ege", ignore = true)
    @Mapping(target = "isEditableFromEgeListBasic", ignore = true)
    @Mapping(target = "isEditableFromEgeList", ignore = true)
    BoreholeLayer boreholeLayerDtoToBoreholeLayer(BoreholeLayerDTO dto);
}
