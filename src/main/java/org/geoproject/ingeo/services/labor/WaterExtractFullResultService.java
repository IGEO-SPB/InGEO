package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullResultDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractFullResult;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface WaterExtractFullResultService extends TableService<WaterExtractFullResult, WaterExtractFullResultDto> {
    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    WaterExtractFullResultDto getDto(WaterExtractFullResult entity);

    @Override
    void updateFromDtos(List<WaterExtractFullResultDto> dtos);

    @Override
    void updateFromDTO(Sample sample, WaterExtractFullResultDto sourceDto);

    @Override
    List<WaterExtractFullResult> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<WaterExtractFullResultDto> dtos);

    @Override
    void delete(WaterExtractFullResultDto dto);

    @Override
    Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project);

    @Override
    void calculate(List<WaterExtractFullResultDto> dtos);
}
