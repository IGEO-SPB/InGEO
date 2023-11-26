package com.geoproject.igeo.mapper;

import com.geoproject.igeo.config.MapStructConfiguration;
import com.geoproject.igeo.dto.SurveyPointDTO;
import com.geoproject.igeo.models.SurveyPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfiguration.class)
public interface SurveyPointMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "waterSampleResultList", ignore = true)
    SurveyPoint surveyPointDtoToSurveyPoint(SurveyPointDTO dto);
}
