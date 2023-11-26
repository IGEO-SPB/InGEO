package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.GranCompositionDTO;
import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface GranCompositionAreometryService extends TableService<GranCompositionAreometry, GranCompositionDTO> {
    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    GranCompositionDTO getDto(GranCompositionAreometry entity);

    @Override
    void updateFromDtos(List<GranCompositionDTO> dtoList);

    @Override
    void updateFromDTO(Sample sample, GranCompositionDTO sourceDto);

    GranCompositionAreometry getBySample(Sample sample);

    @Override
    List<GranCompositionAreometry> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<GranCompositionDTO> dtos);

    @Override
    void delete(GranCompositionDTO dto);
}