package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.WaterExtractMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractPartial;
import org.geoproject.ingeo.models.labor.WaterExtractPartialResult;
import org.geoproject.ingeo.repositories.labor.WaterExtractPartialRepository;
import org.geoproject.ingeo.repositories.labor.WaterExtractPartialResultRepository;
import org.geoproject.ingeo.services.labor.WaterExtractPartialResultService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_ARCHIVE;

@Service
@RequiredArgsConstructor
public class WaterExtractPartialResultServiceImpl implements WaterExtractPartialResultService {

    private final WaterExtractPartialResultRepository waterExtractPartialResultRepository;
    private final WaterExtractPartialRepository waterExtractPartialRepository;
    private final WaterExtractMapper waterExtractMapper;
    private final CurrentState currentState;

    @Override
    public List getDTOs(SurveyPoint surveyPoint) {
        List<WaterExtractPartialResult> entitiesByProject = waterExtractPartialResultRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<WaterExtractPartialResultDto> waterExtractPartialResultDtos = waterExtractMapper.waterExtractPartialResultToWaterExtractPartialResultDto(entitiesByProject);

        return waterExtractPartialResultDtos;
    }

    @Override
    public WaterExtractPartialResultDto getDto(WaterExtractPartialResult entity) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDto"));
    }

    @Override
    @Transactional
    public void updateFromDtos(List<WaterExtractPartialResultDto> dtos) {
        var updatedObjects = waterExtractPartialResultRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        waterExtractMapper.updateWaterExtractPartialResultFromWaterExtractPartialResultDto(updatedObjects, dtos);

        waterExtractPartialResultRepository.saveAll(updatedObjects);

        updatedObjects.forEach(waterExtractPartialResult -> {
            Optional<WaterExtractPartial> waterExtractPartial =
                    waterExtractPartialRepository.findBySurveyPointNumberAndLaborNumberAndIsNotArchive(waterExtractPartialResult.getSample().getSurveyPoint().getPointNumber(),
                            waterExtractPartialResult.getSample().getLaborNumber());

            waterExtractPartial.ifPresent(sample -> sample.setIsBlocked(waterExtractPartialResult.getIsBlocked()));
        });
    }

    @Override
    public void updateFromDTO(Sample sample, WaterExtractPartialResultDto sourceDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDTO"));
    }

    @Override
    public List<WaterExtractPartialResult> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getEntitiesBySurveyPoint"));
    }

    @Override
    public void create(List<WaterExtractPartialResultDto> dtos) {
        List<WaterExtractPartialResult> waterExtractPartialResults = waterExtractMapper.waterExtractPartialResultDtoToWaterExtractPartialResult(dtos);

        waterExtractPartialResults.forEach(waterExtractPartialResult -> {
            waterExtractPartialResult.setIsArchive(Boolean.FALSE);
            waterExtractPartialResult.setIsBlocked(Boolean.FALSE);
        });

        waterExtractPartialResultRepository.saveAll(waterExtractPartialResults);
    }

    @Override
    @Transactional
    public void delete(WaterExtractPartialResultDto dto) {
        Optional<WaterExtractPartialResult> deletedWaterExtractPartialResult = waterExtractPartialResultRepository.findBySurveyPointNumberAndLaborNumberAndIsNotArchive(dto.getSurveyPointNumber(), dto.getLaborNumber());

        deletedWaterExtractPartialResult.ifPresent(entity -> {
            entity.setIsArchive(ENTITY_IS_ARCHIVE);

            waterExtractPartialResultRepository.save(entity);

            Optional<WaterExtractPartial> deletedWaterExtractPartial = waterExtractPartialRepository.findBySampleAndNotArchive(entity.getSample());

            deletedWaterExtractPartial.ifPresent(resultEntity -> {
                resultEntity.setIsArchive(ENTITY_IS_ARCHIVE);

                waterExtractPartialRepository.save(resultEntity);
            });
        });
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<WaterExtractPartialResultDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("calculate"));
    }
}
