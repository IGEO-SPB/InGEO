package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractPartial;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface WaterExtractPartialService extends TableService<WaterExtractPartial, WaterExtractPartialDto> {

    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    WaterExtractPartialDto getDto(WaterExtractPartial entity);

    @Override
    void updateFromDtos(List<WaterExtractPartialDto> dtos);

    @Override
    void updateFromDTO(Sample sample, WaterExtractPartialDto sourceDto);

    @Override
    List<WaterExtractPartial> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<WaterExtractPartialDto> dtos);

    @Override
    void delete(WaterExtractPartialDto dto);

    @Override
    Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project);

    @Override
    void calculate(List<WaterExtractPartialDto> dtos);
}
