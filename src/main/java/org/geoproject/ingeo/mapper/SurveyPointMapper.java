package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.SurveyPointDTO;
import org.geoproject.ingeo.models.SurveyPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfiguration.class)
public interface SurveyPointMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "waterSampleResultList", ignore = true)
    SurveyPoint surveyPointDtoToSurveyPoint(SurveyPointDTO dto);
}
