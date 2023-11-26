package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.ShearDto;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.Shear;

import java.util.List;

public interface ShearWaterContentCutRingService extends MethodViewService<Shear, ShearDto> {

    @Override
    Shear getBySample(Sample sample);

    @Override
    List<Shear> getAllBySample(Sample sample);

    @Override
    void update(Shear object);

    @Override
    void updateFromDTO(ShearDto dto, Sample sample);

    @Override
    ShearDto getDto(Shear entity);

    @Override
    void calculate(Sample sample, ShearDto dto);

    @Override
    Shear getBySampleAndNumber(Sample sample, Integer number);

    @Override
    List<Shear> getByProject(Project currentProject);
}
