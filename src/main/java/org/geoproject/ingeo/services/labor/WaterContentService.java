package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterContent;

public interface WaterContentService extends MethodViewService<WaterContent, WaterContentDTO> {

    @Override
    WaterContent getBySample(Sample sample);

    @Override
    void update(WaterContent object);

    @Override
    void updateFromDTO(WaterContentDTO dto, Sample sample);

    WaterContentDTO getDto(WaterContent waterContent);

    @Override
    void calculate(Sample sample, WaterContentDTO dto);
}
