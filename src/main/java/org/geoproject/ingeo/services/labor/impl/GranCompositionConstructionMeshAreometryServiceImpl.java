package org.geoproject.ingeo.services.tableViews.impl;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshAreometryResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.GranCompositionConstructionMeshAreometryMapper;
import org.geoproject.ingeo.models.*;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMeshAreometry;
import org.geoproject.ingeo.repositories.labor.GranCompositionConstructionMeshAreometryRepository;
import org.geoproject.ingeo.repositories.SamplesRepository;
import org.geoproject.ingeo.services.tableViews.GranCompositionConstructionMeshAreometryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CommonsLog
@RequiredArgsConstructor
public class GranCompositionConstructionMeshAreometryServiceImpl implements GranCompositionConstructionMeshAreometryService {

    private final GranCompositionConstructionMeshAreometryRepository targetModelRepository;
    private final SamplesRepository samplesRepository;
    private final GranCompositionConstructionMeshAreometryMapper targetMapper;

    @Override
    public void updateFromDtos(List<ConstructionMeshAreometryResultDto> constructionMeshResultDtos) {
        List<String> dtoLaborNumbers = constructionMeshResultDtos.stream()
                .map(ConstructionMeshAreometryResultDto::getLaborNumber)
                .toList();

        var updatedObjects = targetModelRepository.findAllByLaborNumberIn(dtoLaborNumbers);

        log.info("updated gran composition constructon mesh areometry models amount: " + updatedObjects.size());

        targetMapper.updateModelFromDto(updatedObjects, constructionMeshResultDtos);

        targetModelRepository.saveAll(updatedObjects);
    }

    @Override
    public ConstructionMeshAreometryResultDto getDto(GranCompositionConstructionMeshAreometry entity) {
//        GranCompositionConstructionMeshAreometry bySampleId = granCompositionConstructionMeshRepository.findBySampleId(entity.getId())
//                .orElseThrow(() -> new RuntimeException("GranCompositionAreometry entity not found"));
//                onstructionMeshToConstruct
        return targetMapper.granCompositionConstructionMeshAreometryToConstructionMeshAreometryResultDto(entity);
    }

    @Override
    public List<ConstructionMeshAreometryResultDto> getDTOs(SurveyPoint surveyPoint) {
        List<GranCompositionConstructionMeshAreometry> entitiesBySurveyPoint = getEntitiesBySurveyPoint(surveyPoint);

        List<ConstructionMeshAreometryResultDto> dtos = targetMapper.granCompositionConstructionMeshAreometryToConstructionMeshAreometryResultDto(entitiesBySurveyPoint);

        for (var dto : dtos) {
            Sample sample = samplesRepository.findByLaborNumber(dto.getLaborNumber())
                    .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("Образец")));
            dto.setSurveyPointNumber(surveyPoint.getPointNumber());
            dto.setDepthMin(sample.getDepthMin());
            dto.setDepthMax(sample.getDepthMax());
        }

        return dtos;
    }

    @Override
    public GranCompositionConstructionMeshAreometry getBySample(Sample sample) {
        return targetModelRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new NotFoundException("GranCompositionConstructionMeshAreometry entity not found"));
    }

    @Override
    public void updateFromDTO(Sample sample, ConstructionMeshAreometryResultDto sourceDto) {
        GranCompositionConstructionMeshAreometry foundGranCompositionConstructionMeshAreometry = targetModelRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new RuntimeException("GranCompositionConstructionMeshAreometry entity not found"));

        targetMapper.updateGranCompositionConstructionMeshAreometryFromConstructionMeshAreometryResultDto(foundGranCompositionConstructionMeshAreometry, sourceDto);

        targetModelRepository.save(foundGranCompositionConstructionMeshAreometry);
    }

    @Override
    public List<GranCompositionConstructionMeshAreometry> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        return targetModelRepository.findBySurveyPoint(surveyPoint.getId());
    }

    @Override
    public void create(List<ConstructionMeshAreometryResultDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));
    }

    @Override
    public void delete(ConstructionMeshAreometryResultDto dto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("delete"));
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<ConstructionMeshAreometryResultDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("calculate"));
    }
}
