package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterSampleDto;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterSample;
import org.geoproject.ingeo.models.labor.WaterSampleResult;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface WaterSampleService extends TableService<WaterSample, WaterSampleDto> {
    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    WaterSampleDto getDto(WaterSample entity);

    @Override
    void updateFromDtos(List<WaterSampleDto> dtos);

    @Override
    void updateFromDTO(Sample sample, WaterSampleDto sourceDto);

    @Override
    List<WaterSample> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<WaterSampleDto> dtos);

    void create(WaterSampleResult waterSampleResult);

    WaterSample getByLaborNumberAndProjectId(String laborNumber, Long projectId);

    WaterSample getByLaborNumberAndSurveyPoint(String laborNumber, SurveyPoint surveyPoint);

    @Override
    void delete(WaterSampleDto waterSampleDto);
}
