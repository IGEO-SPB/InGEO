package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialResultDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractPartialResult;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface WaterExtractPartialResultService extends TableService<WaterExtractPartialResult, WaterExtractPartialResultDto> {
    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    WaterExtractPartialResultDto getDto(WaterExtractPartialResult entity);

    @Override
    void updateFromDtos(List<WaterExtractPartialResultDto> dtos);

    @Override
    void updateFromDTO(Sample sample, WaterExtractPartialResultDto sourceDto);

    @Override
    List<WaterExtractPartialResult> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<WaterExtractPartialResultDto> dtos);

    @Override
    void delete(WaterExtractPartialResultDto dto);

    @Override
    Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project);

    @Override
    void calculate(List<WaterExtractPartialResultDto> dtos);
}
