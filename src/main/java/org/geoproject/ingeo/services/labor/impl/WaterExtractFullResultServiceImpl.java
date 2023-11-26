package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.WaterExtractMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractFull;
import org.geoproject.ingeo.models.labor.WaterExtractFullResult;
import org.geoproject.ingeo.repositories.labor.WaterExtractFullRepository;
import org.geoproject.ingeo.repositories.labor.WaterExtractFullResultRepository;
import org.geoproject.ingeo.services.labor.WaterExtractFullResultService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_ARCHIVE;

@Service
@RequiredArgsConstructor
public class WaterExtractFullResultServiceImpl implements WaterExtractFullResultService {

    private final WaterExtractFullResultRepository waterExtractFullResultRepository;
    private final WaterExtractFullRepository waterExtractFullRepository;
    private final WaterExtractMapper waterExtractMapper;
    private final CurrentState currentState;

    @Override
    public List getDTOs(SurveyPoint surveyPoint) {
        List<WaterExtractFullResult> entitiesByProject = waterExtractFullResultRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<WaterExtractFullResultDto> waterExtractFullResultDtos = waterExtractMapper.waterExtractFullResultToWaterExtractFullResultDto(entitiesByProject);

        return waterExtractFullResultDtos;
    }

    @Override
    public WaterExtractFullResultDto getDto(WaterExtractFullResult entity) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDto"));
    }

    @Override
    @Transactional
    public void updateFromDtos(List<WaterExtractFullResultDto> dtos) {
        var updatedObjects = waterExtractFullResultRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        waterExtractMapper.updateWaterExtractFullResultFromWaterExtractFullResultDto(updatedObjects, dtos);

        waterExtractFullResultRepository.saveAll(updatedObjects);

        updatedObjects.forEach(waterExtractFullResult -> {
            Optional<WaterExtractFull> waterExtractPartial =
                    waterExtractFullRepository.findBySurveyPointNumberAndLaborNumberAndIsNotArchive(waterExtractFullResult.getSample().getSurveyPoint().getPointNumber(),
                            waterExtractFullResult.getSample().getLaborNumber());

            waterExtractPartial.ifPresent(sample -> sample.setIsBlocked(waterExtractFullResult.getIsBlocked()));
        });
    }

    @Override
    public void updateFromDTO(Sample sample, WaterExtractFullResultDto sourceDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDTO"));
    }

    @Override
    public List<WaterExtractFullResult> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getEntitiesBySurveyPoint"));
    }

    @Override
    public void create(List<WaterExtractFullResultDto> dtos) {
        List<WaterExtractFullResult> waterExtractFullResults = waterExtractMapper.waterExtractFullResultDtoToWaterExtractFullResult(dtos);

        waterExtractFullResults.forEach(waterExtractPartialResult -> {
            waterExtractPartialResult.setIsArchive(Boolean.FALSE);
            waterExtractPartialResult.setIsBlocked(Boolean.FALSE);
        });

        waterExtractFullResultRepository.saveAll(waterExtractFullResults);
    }

    @Override
    @Transactional
    public void delete(WaterExtractFullResultDto dto) {
        Optional<WaterExtractFullResult> deletedWaterExtractPartialResult = waterExtractFullResultRepository.findBySurveyPointNumberAndLaborNumberAndIsNotArchive(dto.getSurveyPointNumber(), dto.getLaborNumber());

        deletedWaterExtractPartialResult.ifPresent(entity -> {
            entity.setIsArchive(ENTITY_IS_ARCHIVE);

            waterExtractFullResultRepository.save(entity);

            Optional<WaterExtractFull> deletedWaterExtractPartial = waterExtractFullRepository.findBySampleAndNotArchive(entity.getSample());

            deletedWaterExtractPartial.ifPresent(resultEntity -> {
                resultEntity.setIsArchive(ENTITY_IS_ARCHIVE);

                waterExtractFullRepository.save(resultEntity);
            });
        });
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<WaterExtractFullResultDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("calculate"));
    }
}
