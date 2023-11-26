package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshResultDto;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMesh;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface GranCompositionConstructionMeshService extends TableService<GranCompositionConstructionMesh, ConstructionMeshResultDto> {
    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    ConstructionMeshResultDto getDto(GranCompositionConstructionMesh entity);

    @Override
    void updateFromDtos(List<ConstructionMeshResultDto> dtos);

    @Override
    void updateFromDTO(Sample sample, ConstructionMeshResultDto sourceDto);

    GranCompositionConstructionMesh getBySample(Sample sample);

    @Override
    List<GranCompositionConstructionMesh> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    @Override
    void create(List<ConstructionMeshResultDto> dtos);

    @Override
    void delete(ConstructionMeshResultDto dto);
}