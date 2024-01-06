package org.geoproject.ingeo.mapper;

import org.geoproject.ingeo.config.MapStructConfiguration;
import org.geoproject.ingeo.dto.SurveyPointDto;
import org.geoproject.ingeo.models.SurveyPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfiguration.class)
public interface SurveyPointMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "waterSampleResultList", ignore = true)
    SurveyPoint surveyPointDtoToSurveyPoint(SurveyPointDto dto);

    @Mapping(target = "boreholeLayerList", ignore = true)
    @Mapping(target = "sampleList", ignore = true)
    SurveyPointDto surveyPointToSurveyPointDto(SurveyPoint surveyPoint);

    List<SurveyPointDto> surveyPointToSurveyPointDto(List<SurveyPoint> surveyPoints);
}
