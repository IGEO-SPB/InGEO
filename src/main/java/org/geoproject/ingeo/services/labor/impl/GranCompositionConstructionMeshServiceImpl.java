package org.geoproject.ingeo.services.tableViews.impl;

import org.geoproject.ingeo.dto.methodDtos.ConstructionMeshResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.GranCompositionConstructionMeshMapper;
import org.geoproject.ingeo.models.labor.GranCompositionConstructionMesh;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.labor.GranCompositionConstructionMeshRepository;
import org.geoproject.ingeo.repositories.SamplesRepository;
import org.geoproject.ingeo.services.tableViews.GranCompositionConstructionMeshService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GranCompositionConstructionMeshServiceImpl implements GranCompositionConstructionMeshService {

    private final GranCompositionConstructionMeshRepository granCompositionConstructionMeshRepository;
    private final SamplesRepository samplesRepository;
    private final GranCompositionConstructionMeshMapper granCompositionConstructionMeshMapper;

    @Override
    public void updateFromDtos(List<ConstructionMeshResultDto> constructionMeshResultDtos) {
        List<String> dtoLaborNumbers = constructionMeshResultDtos.stream()
                .map(ConstructionMeshResultDto::getLaborNumber)
                .toList();

        List<GranCompositionConstructionMesh> updatedObjects = granCompositionConstructionMeshRepository.findAllByIdIn(dtoLaborNumbers);

        granCompositionConstructionMeshMapper.updateGranCompositionConstructionMeshFromConstructionMeshResultDto(updatedObjects, constructionMeshResultDtos);

        granCompositionConstructionMeshRepository.saveAll(updatedObjects);
    }

    @Override
    public ConstructionMeshResultDto getDto(GranCompositionConstructionMesh entity) {
//        GranCompositionConstructionMesh bySampleId = granCompositionConstructionMeshRepository.findBySampleId(entity.getId())
//                .orElseThrow(() -> new RuntimeException("GranCompositionAreometry entity not found"));

        return granCompositionConstructionMeshMapper.granCompositionConstructionMeshToConstructionMeshResultDto(entity);
    }

    @Override
    public List<ConstructionMeshResultDto> getDTOs(SurveyPoint surveyPoint) {
        List<GranCompositionConstructionMesh> entitiesBySurveyPoint = getEntitiesBySurveyPoint(surveyPoint);

        List<ConstructionMeshResultDto> dtos = granCompositionConstructionMeshMapper.granCompositionConstructionMeshToConstructionMeshResultDto(entitiesBySurveyPoint);

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
    public GranCompositionConstructionMesh getBySample(Sample sample) {
        return granCompositionConstructionMeshRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new NotFoundException("GranCompositionConstructionMesh entity not found"));
    }

    @Override
    public void updateFromDTO(Sample sample, ConstructionMeshResultDto sourceDto) {
        GranCompositionConstructionMesh foundGranCompositionConstructionMesh = granCompositionConstructionMeshRepository.findBySampleId(sample.getId())
                .orElseThrow(() -> new RuntimeException("GranCompositionConstructionMesh entity not found"));

        granCompositionConstructionMeshMapper.updateGranCompositionConstructionMeshFromConstructionMeshResultDto(foundGranCompositionConstructionMesh, sourceDto);

        granCompositionConstructionMeshRepository.save(foundGranCompositionConstructionMesh);
    }

    @Override
    public List<GranCompositionConstructionMesh> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        return granCompositionConstructionMeshRepository.findBySurveyPoint(surveyPoint.getId());
    }

    @Override
    public void create(List<ConstructionMeshResultDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));
    }

    @Override
    public void delete(ConstructionMeshResultDto dto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("delete"));
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<ConstructionMeshResultDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("calculate"));
    }
}