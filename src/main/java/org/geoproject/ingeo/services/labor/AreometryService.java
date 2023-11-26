package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.AreometryDTO;
import org.geoproject.ingeo.models.labor.Areometry;
import org.geoproject.ingeo.models.Sample;

public interface AreometryService extends MethodViewService<Areometry, AreometryDTO> {

    @Override
    Areometry getBySample(Sample sample);

    @Override
    void update(Areometry object);

    @Override
    void updateFromDTO(AreometryDTO dto, Sample sample);

    @Override
    AreometryDTO getDto(Areometry entity);

    @Override
    void calculate(Sample sample, AreometryDTO dto);
}
