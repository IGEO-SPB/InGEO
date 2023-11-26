package org.geoproject.ingeo.services.methodViews;

import org.geoproject.ingeo.dto.methodDtos.DensityDTO;
import org.geoproject.ingeo.models.labor.Density;
import org.geoproject.ingeo.models.Sample;

public interface DensityService extends MethodViewService<Density, DensityDTO> {

    @Override
    Density getBySample(Sample sample);

    @Override
    void update(Density object);

    @Override
    void updateFromDTO(DensityDTO dto, Sample sample);

    @Override
    DensityDTO getDto(Density density);

    @Override
    void calculate(Sample sample, DensityDTO dto);
}
