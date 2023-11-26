package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshDTO;
import org.geoproject.ingeo.models.labor.ConstructionMesh;
import org.geoproject.ingeo.models.Sample;

public interface ConstructionMeshService
        extends MethodViewService<ConstructionMesh, ConstructionMeshDTO> {

    @Override
    ConstructionMesh getBySample(Sample sample);

    @Override
    void update(ConstructionMesh object);

    @Override
    void updateFromDTO(ConstructionMeshDTO constructionMeshDTO, Sample sample);

    @Override
    void calculate(Sample sample, ConstructionMeshDTO constructionMeshDTO);
}