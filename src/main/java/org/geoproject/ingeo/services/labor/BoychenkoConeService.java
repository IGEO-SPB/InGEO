package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.BoychenkoConeDTO;
import org.geoproject.ingeo.models.labor.BoychenkoCone;
import org.geoproject.ingeo.models.Sample;

public interface BoychenkoConeService extends MethodViewService<BoychenkoCone, BoychenkoConeDTO> {
    @Override
    BoychenkoCone getBySample(Sample sample);

    @Override
    void update(BoychenkoCone object);

    @Override
    void updateFromDTO(BoychenkoConeDTO dto, Sample sample);

    BoychenkoConeDTO getDto(BoychenkoCone boychenkoCone);

    @Override
    void calculate(Sample sample, BoychenkoConeDTO dto);
}
