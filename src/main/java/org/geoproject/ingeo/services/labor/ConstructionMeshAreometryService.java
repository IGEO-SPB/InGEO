package org.geoproject.ingeo.services.methodViews;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshAreometryDto;
import org.geoproject.ingeo.models.labor.ConstructionMeshAreometry;
import org.geoproject.ingeo.models.Sample;

public interface ConstructionMeshAreometryService
        extends MethodViewService<ConstructionMeshAreometry, ConstructionMeshAreometryDto> {

    @Override
    ConstructionMeshAreometry getBySample(Sample sample);

    @Override
    void update(ConstructionMeshAreometry object);

    @Override
    void updateFromDTO(ConstructionMeshAreometryDto constructionMeshDTO, Sample sample);

    @Override
    void calculate(Sample sample, ConstructionMeshAreometryDto constructionMeshAreometryDTO);
}