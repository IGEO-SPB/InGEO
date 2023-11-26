package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractFull;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface WaterExtractFullService extends TableService<WaterExtractFull, WaterExtractFullDto> {

    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    WaterExtractFullDto getDto(WaterExtractFull entity);

    @Override
    void updateFromDtos(List<WaterExtractFullDto> dtos);

    @Override
    void updateFromDTO(Sample sample, WaterExtractFullDto sourceDto);

    @Override
    List<WaterExtractFull> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<WaterExtractFullDto> dtos);

    @Override
    void delete(WaterExtractFullDto dto);

    @Override
    Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project);

    @Override
    void calculate(List<WaterExtractFullDto> dtos);
}
