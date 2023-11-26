package org.geoproject.ingeo.services.methodViews;

import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.models.labor.OrganicMatter;
import org.geoproject.ingeo.models.Sample;

public interface OrganicMatterService extends MethodViewService<OrganicMatter, OrganicMatterDTO> {
    @Override
    OrganicMatter getBySample(Sample sample);

    @Override
    void update(OrganicMatter object);

    @Override
    void updateFromDTO(OrganicMatterDTO dto, Sample sample);

    OrganicMatterDTO getDto(OrganicMatter entity);

    @Override
    void calculate(Sample sample, OrganicMatterDTO dto);
}
