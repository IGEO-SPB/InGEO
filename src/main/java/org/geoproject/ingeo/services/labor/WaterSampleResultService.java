package org.geoproject.ingeo.services.tableViews;

import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterSampleResult;

import java.util.List;

public interface WaterSampleResultService extends TableService<WaterSampleResult, WaterSampleResultDto> {
    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    WaterSampleResultDto getDto(WaterSampleResult entity);

    @Override
    void updateFromDtos(List<WaterSampleResultDto> dtos);

    @Override
    void updateFromDTO(Sample sample, WaterSampleResultDto sourceDto);

    @Override
    List<WaterSampleResult> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<WaterSampleResultDto> dtos);

    @Override
    void delete(WaterSampleResultDto dto);

    @Override
    Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project);
}
