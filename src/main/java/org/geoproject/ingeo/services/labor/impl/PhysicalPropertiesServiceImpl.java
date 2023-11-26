package org.geoproject.ingeo.services.tableViews.impl;

import org.geoproject.ingeo.dto.methodDtos.BoychenkoConeDTO;
import org.geoproject.ingeo.dto.methodDtos.DensityDTO;
import org.geoproject.ingeo.dto.methodDtos.OrganicMatterDTO;
import org.geoproject.ingeo.dto.methodDtos.PhysicalPropertiesDTO;
import org.geoproject.ingeo.dto.methodDtos.RingDensityDTO;
import org.geoproject.ingeo.dto.methodDtos.WaterContentDTO;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.PhysicalPropertiesMapper;
import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
import org.geoproject.ingeo.models.labor.PhysicalProperties;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterContent;
import org.geoproject.ingeo.models.classificators.BoychenkoConeLiquidityConsistency;
import org.geoproject.ingeo.repositories.labor.PhysicalPropertiesRepository;
import org.geoproject.ingeo.repositories.SamplesRepository;
import org.geoproject.ingeo.services.classificators.BoychenkoConeLiquidityConsistencyService;
import org.geoproject.ingeo.services.methodViews.WaterContentService;
import org.geoproject.ingeo.services.tableViews.GranCompositionAreometryService;
import org.geoproject.ingeo.services.tableViews.PhysicalPropertiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PhysicalPropertiesServiceImpl implements PhysicalPropertiesService {
    private final PhysicalPropertiesRepository physicalPropertiesRepository;
    private final SamplesRepository samplesRepository;
    private final PhysicalPropertiesMapper mapper;
    private final GranCompositionAreometryService granCompositionAreometryService;
    private final WaterContentService waterContentService;
    private final BoychenkoConeLiquidityConsistencyService boychenkoConeLiquidityConsistencyService;

    @Override
    @Transactional
    public PhysicalProperties getBySample(Sample sample) {
        return physicalPropertiesRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("PhysicalProperties")));
    }

    @Override
    @Transactional
    public PhysicalProperties getBySampleAndUpdate(Sample sample,
                                          DensityDTO densityDTO,
                                          WaterContentDTO waterContentDTO,
                                          BoychenkoConeDTO boychenkoConeDTO,
                                          RingDensityDTO ringDensityDTO,
                                          OrganicMatterDTO organicMatterDTO
    ) {
        Optional<PhysicalProperties> byId = physicalPropertiesRepository.findBySampleId(sample.getId());

        if (byId.isEmpty()) {
            PhysicalProperties foundPhysicalProperties = new PhysicalProperties();

            mapper.updatePhysicalPropertiesFromSample(foundPhysicalProperties, sample,
                    densityDTO,
                    waterContentDTO,
                    boychenkoConeDTO,
                    ringDensityDTO,
                    organicMatterDTO);

            return physicalPropertiesRepository.save(foundPhysicalProperties);
        }

        return byId.get();
    }


    @Override
    public void updateFromDtos(List<PhysicalPropertiesDTO> dtoList) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDtos"));
    }

    @Override
    @Transactional
    public void updateFromDTO(Sample sample, PhysicalPropertiesDTO sourceDto) {
        var byLaborNumber = physicalPropertiesRepository.findByLaborNumber(sourceDto.getLaborNumber());

        mapper.updatePhysicalProperties(byLaborNumber, sourceDto);

        physicalPropertiesRepository.save(byLaborNumber);
    }

    @Override
    public List<PhysicalProperties> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        List<PhysicalProperties> physicalPropertiesList = physicalPropertiesRepository.findByProject(surveyPoint.getProject());

        return physicalPropertiesList;
    }

    @Override
    public PhysicalPropertiesDTO getDto(PhysicalProperties entity) {
        return mapper.physicalPropertiesToPhysicalPropertiesDTO(entity);
    }

    private void setBoychenkoConeLiquidityConsistency(List<PhysicalPropertiesDTO> dtos) {
        for (var dto : dtos) {
            BoychenkoConeLiquidityConsistency undisturbedConsistency = boychenkoConeLiquidityConsistencyService
                    .findByBoychenkoConeImmersionDepth(dto.getUndisturbedStrBoychenkoConeImmersionDepthAverage());

            BoychenkoConeLiquidityConsistency brokenConsistency = boychenkoConeLiquidityConsistencyService
                    .findByBoychenkoConeImmersionDepth(dto.getBrokenStrBoychenkoConeImmersionDepthAverage());

            if (undisturbedConsistency != null && brokenConsistency != null) {
                dto.setUndisturbedStructureCbConsistency(undisturbedConsistency.getCbConsistency());
                dto.setBrokenStructureCbConsistency(brokenConsistency.getCbConsistency());
            }
        }
    }

    private void setBoychenkoConeLiquidityIndexName(List<PhysicalPropertiesDTO> dtos) {
        for (var dto : dtos) {
            String undisturbedCbLiquidityIndexName = boychenkoConeLiquidityConsistencyService
                    .defineCbLiquidityIndexName(dto.getUndisturbedStrBoychenkoConeImmersionDepthAverage());
            String brokenCbLiquidityIndexName = boychenkoConeLiquidityConsistencyService
                    .defineCbLiquidityIndexName(dto.getBrokenStrBoychenkoConeImmersionDepthAverage());

            dto.setUndisturbedStructureCbLiquidityIndexName(undisturbedCbLiquidityIndexName);
            dto.setBrokenStructureCbLiquidityIndexName(brokenCbLiquidityIndexName);
        }
    }

    @Override
    public List<PhysicalPropertiesDTO> getDTOs(SurveyPoint surveyPoint) {
        List<PhysicalProperties> entitiesBySurveyPoint = getEntitiesBySurveyPoint(surveyPoint);

        List<PhysicalPropertiesDTO> dtos = mapper.physicalPropertiesToPhysicalPropertiesDTO(entitiesBySurveyPoint);

        for (var dto : dtos) {
            Sample sample = samplesRepository.findByLaborNumber(dto.getLaborNumber())
                    .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.SAMPLE_NOT_FOUND_EXCEPTION.getMessage()));

            dto.setSurveyPointNumber(surveyPoint.getPointNumber());
            dto.setDepthMin(sample.getDepthMin());
            dto.setDepthMax(sample.getDepthMax());

            try {
                GranCompositionAreometry granCompositionAreometry = granCompositionAreometryService.getBySample(sample);

                dto.setSum_2_005(granCompositionAreometry.getSum_2_005());
                dto.setSum_005_0002_old_005_0005(granCompositionAreometry.getSum_005_0002_old_005_0005());
                dto.setSum_less_0002_old_less_0005(granCompositionAreometry.getSum_less_0002_old_less_0005());
//            dto.setSand(granCompositionAreometry.getIsSand());
            } catch (NotFoundException e) {
                log.warn(e.getMessage());
            }

            PhysicalProperties physicalProperties = getBySample(sample);

            dto.setBrokenStrBoychenkoConeImmersionDepthAverage(physicalProperties.getBrokenStrBoychenkoConeImmersionDepthAverage());
            dto.setUndisturbedStrBoychenkoConeImmersionDepthAverage(physicalProperties.getUndisturbedStrBoychenkoConeImmersionDepthAverage());

            WaterContent waterContent = waterContentService.getBySample(sample);
            dto.setLiquidityIndex(waterContent.getLiquidityIndex());
        }

        setBoychenkoConeLiquidityConsistency(dtos);
        setBoychenkoConeLiquidityIndexName(dtos);

        return dtos;
    }

    @Override
    public void create(List<PhysicalPropertiesDTO> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));
    }

    @Override
    public void delete(PhysicalPropertiesDTO dto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("delete"));
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<PhysicalPropertiesDTO> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("calculate"));
    }
}
