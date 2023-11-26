package org.geoproject.ingeo.services.labor;

import org.geoproject.ingeo.dto.methodDtos.BoychenkoConeDTO;
import org.geoproject.ingeo.dto.methodDtos.DensityDTO;
import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.dto.methodDtos.PhysicalPropertiesDTO;
import org.geoproject.ingeo.dto.methodDtos.RingDensityDTO;
import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.models.labor.PhysicalProperties;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.services.TableService;

import java.util.List;

public interface PhysicalPropertiesService extends TableService<PhysicalProperties, PhysicalPropertiesDTO> {

    @Override
    List getDTOs(SurveyPoint surveyPoint);

    @Override
    void updateFromDtos(List<PhysicalPropertiesDTO> dtoList);

    @Override
    PhysicalPropertiesDTO getDto(PhysicalProperties entity);

    PhysicalProperties getBySample(Sample sample);

    @Override
    List<PhysicalProperties> getEntitiesBySurveyPoint(SurveyPoint surveyPoint);

    PhysicalProperties getBySampleAndUpdate(Sample sample,
                                            DensityDTO densityDTO,
                                            WaterContentDTO waterContentDTO,
                                            BoychenkoConeDTO boychenkoConeDTO,
                                            RingDensityDTO ringDensityDTO,
                                            OrganicMatterDTO organicMatterDTO);

    @Override
    void create(List<PhysicalPropertiesDTO> dtos);

    @Override
    void delete(PhysicalPropertiesDTO dto);
}