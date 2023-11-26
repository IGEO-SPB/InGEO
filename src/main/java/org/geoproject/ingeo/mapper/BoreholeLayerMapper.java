package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDTO;
import org.geoproject.ingeo.models.BoreholeLayer;
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
