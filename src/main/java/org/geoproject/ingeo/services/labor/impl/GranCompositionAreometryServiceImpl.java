package org.geoproject.ingeo.services.tableViews.impl;

import org.geoproject.ingeo.dto.methodDtos.GranCompositionDTO;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.GranCompositionAreometryMapper;
import org.geoproject.ingeo.models.labor.GranCompositionAreometry;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.labor.GranCompositionAreometryRepository;
import org.geoproject.ingeo.repositories.SamplesRepository;
import org.geoproject.ingeo.services.tableViews.GranCompositionAreometryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GranCompositionAreometryServiceImpl implements GranCompositionAreometryService {
    private final GranCompositionAreometryRepository granCompositionAreometryRepository;
    private final SamplesRepository samplesRepository;
    private final GranCompositionAreometryMapper mapper;

    public GranCompositionAreometryServiceImpl(GranCompositionAreometryRepository repository,
                                               SamplesRepository samplesRepository,
                                               GranCompositionAreometryMapper mapper) {
        this.granCompositionAreometryRepository = repository;
        this.samplesRepository = samplesRepository;
        this.mapper = mapper;
    }

    @Override
    public void updateFromDtos(List<GranCompositionDTO> dtos) {
        List<String> dtoLaborNumbers = dtos.stream()
                .map(GranCompositionDTO::getLaborNumber)
                .toList();

        List<GranCompositionAreometry> updatedObjects = granCompositionAreometryRepository.findAllByIdIn(dtoLaborNumbers);

        mapper.updateGranCompositionAreometry(updatedObjects, dtos);

        granCompositionAreometryRepository.saveAll(updatedObjects);
    }

    @Override
    @Transactional
    public void updateFromDTO(Sample sample, GranCompositionDTO granCompositionDTO) {
        GranCompositionAreometry bySample = granCompositionAreometryRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new RuntimeException("GranCompositionAreometry entity not found"));

        mapper.updateGranCompositionAreometry(bySample, granCompositionDTO);

        granCompositionAreometryRepository.save(bySample);
    }

    @Override
    public GranCompositionDTO getDto(GranCompositionAreometry entity) {
        GranCompositionAreometry bySampleId = granCompositionAreometryRepository.findBySampleId(entity.getId())
                .orElseThrow(() -> new RuntimeException("GranCompositionAreometry entity not found"));

        return mapper.granCompositionAreometryToGranCompositionDTO(bySampleId);
    }

    @Override
    public List<GranCompositionDTO> getDTOs(SurveyPoint surveyPoint) {
        List<GranCompositionAreometry> entitiesBySurveyPoint = getEntitiesBySurveyPoint(surveyPoint);

        List<GranCompositionDTO> dtos = mapper.granCompositionAreometryToGranCompositionDTO(entitiesBySurveyPoint);

        for (var dto : dtos) {
            Sample sample = samplesRepository.findByLaborNumber(dto.getLaborNumber())
                    .orElseThrow(() -> new RuntimeException("Sample entity not found"));
            dto.setSurveyPointNumber(surveyPoint.getPointNumber());
            dto.setDepthMin(sample.getDepthMin());
            dto.setDepthMax(sample.getDepthMax());
        }

        return dtos;
    }

    @Override
    public GranCompositionAreometry getBySample(Sample sample) {
        return granCompositionAreometryRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.GRAN_COMPOSITION_AREOMETRY_NOT_FOUND_EXCEPTION.getMessage()));
    }

    @Override
    public List<GranCompositionAreometry> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        return granCompositionAreometryRepository.findBySurveyPoint(surveyPoint.getId());
    }

    @Override
    public void create(List<GranCompositionDTO> dtos) {
        throw new NotImplemented("Метод create не реализован");
    }

    @Override
    public void delete(GranCompositionDTO dto) {
        throw new NotImplemented("Метод delete не реализован");
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<GranCompositionDTO> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("calculate"));
    }
}