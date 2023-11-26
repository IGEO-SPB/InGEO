package org.geoproject.ingeo.services.methodViews;

import org.geoproject.ingeo.dto.methodDtos.RingDensityDTO;
import org.geoproject.ingeo.models.labor.RingDensity;
import org.geoproject.ingeo.models.Sample;

public interface RingDensityService extends MethodViewService<RingDensity, RingDensityDTO> {

    @Override
    RingDensity getBySample(Sample sample);

    @Override
    void update(RingDensity object);

    @Override
    void updateFromDTO(RingDensityDTO dto, Sample sample);

    @Override
    RingDensityDTO getDto(RingDensity ringDensity);

    @Override
    void calculate(Sample sample, RingDensityDTO dto);
}