package org.geoproject.ingeo.services.tableViews;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshAreometryResultDto;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMeshAreometry;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;

import java.util.List;

public interface GranCompositionConstructionMeshAreometryService extends TableService<GranCompositionConstructionMeshAreometry, ConstructionMeshAreometryResultDto> {
    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    ConstructionMeshAreometryResultDto getDto(GranCompositionConstructionMeshAreometry entity);

    @Override
    void updateFromDtos(List<ConstructionMeshAreometryResultDto> dtos);

    @Override
    void updateFromDTO(Sample sample, ConstructionMeshAreometryResultDto sourceDto);

    GranCompositionConstructionMeshAreometry getBySample(Sample sample);

    @Override
    List<GranCompositionConstructionMeshAreometry> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<ConstructionMeshAreometryResultDto> dtos);

    @Override
    void delete(ConstructionMeshAreometryResultDto dto);
}
