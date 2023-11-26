package org.geoproject.ingeo.services.tableViews.impl;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractFullResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.WaterExtractMapper;
import org.geoproject.ingeo.methods.labor.WaterExtractFullMethod;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractFull;
import org.geoproject.ingeo.models.labor.WaterExtractFullResult;
import org.geoproject.ingeo.repositories.labor.WaterExtractFullRepository;
import org.geoproject.ingeo.repositories.labor.WaterExtractFullResultRepository;
import org.geoproject.ingeo.services.tableViews.WaterExtractFullResultService;
import org.geoproject.ingeo.services.tableViews.WaterExtractFullService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_ARCHIVE;

@Service
@RequiredArgsConstructor
public class WaterExtractFullServiceImpl implements WaterExtractFullService {

    private final CurrentState currentState;
    private final WaterExtractMapper waterExtractMapper;

    private final WaterExtractFullRepository waterExtractFullRepository;
    private final WaterExtractFullResultRepository waterExtractFullResultRepository;

    private final WaterExtractFullResultService waterExtractFullResultService;

    @Override
    public List getDTOs(SurveyPoint surveyPoint) {
        List<WaterExtractFull> entitiesByProject = waterExtractFullRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<WaterExtractFullDto> waterExtractFullDtos = waterExtractMapper.waterExtractFullToWaterExtractFullDto(entitiesByProject);

        return waterExtractFullDtos;
    }

    @Override
    public WaterExtractFullDto getDto(WaterExtractFull entity) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDto"));
    }

    @Override
    @Transactional
    public void updateFromDtos(List<WaterExtractFullDto> dtos) {
        List<WaterExtractFull> updatedObjects = waterExtractFullRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<String> updatedLaborNumbers = updatedObjects.stream()
                .map(waterExtractFull -> waterExtractFull.getSample().getLaborNumber())
                .toList();

        List<WaterExtractFullDto> oldDtos = dtos.stream()
                .filter(dto -> updatedLaborNumbers.contains(dto.getLaborNumber()))
                .toList();

        waterExtractMapper.updateWaterExtractFullFromWaterExtractFullDto(updatedObjects, oldDtos);

        waterExtractFullRepository.saveAll(updatedObjects);

        updatedObjects.forEach(updatedObject -> {
                    var updatedResultObject = waterExtractFullResultRepository.findBySampleAndNotArchive(updatedObject.getSample());

                    updatedResultObject.ifPresent(resultObject -> {
                        waterExtractMapper.updateWaterExtractFullResultFromWaterExtractFull(resultObject, updatedObject);

                        resultObject.setSamplingDate(updatedObject.getSamplingDate());

                        waterExtractFullResultRepository.save(resultObject);
                    });
                });

        List<WaterExtractFullDto> newDtos = dtos.stream()
                .filter(dto -> !updatedLaborNumbers.contains(dto.getLaborNumber()) && Objects.nonNull(dto.getLaborNumber()))
                .toList();

        if (!newDtos.isEmpty()) {
            List<WaterExtractFull> newEntities = waterExtractMapper.waterExtractFullDtoToWaterExtractFull(newDtos);

            newEntities.forEach(newEntity -> {
                newEntity.setIsArchive(Boolean.FALSE);
                newEntity.setIsBlocked(Boolean.FALSE);
            });

            waterExtractFullRepository.saveAll(newEntities);

            List<WaterExtractFullResultDto> newWaterExtractFullResultDtos = newEntities.stream()
                    .map(newEntity -> {
                        WaterExtractFullResultDto waterExtractFullResultDto = new WaterExtractFullResultDto();

                        waterExtractFullResultDto.setLaborNumber(newEntity.getSample().getLaborNumber());
                        waterExtractFullResultDto.setSurveyPointNumber(newEntity.getSample().getSurveyPoint().getPointNumber());
                        waterExtractFullResultDto.setDepthFrom(newEntity.getDepthFrom());
                        waterExtractFullResultDto.setDepthTo(newEntity.getDepthTo());
                        waterExtractFullResultDto.setSamplingDate(newEntity.getSamplingDate());

                        return waterExtractFullResultDto;
                    })
                    .collect(Collectors.toList());

            waterExtractFullResultService.create(newWaterExtractFullResultDtos);
        }
    }

    @Override
    public void updateFromDTO(Sample sample, WaterExtractFullDto sourceDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDTO"));
    }

    @Override
    public List<WaterExtractFull> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getEntitiesBySurveyPoint"));
    }

    @Override
    public void create(List<WaterExtractFullDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));
    }

    @Override
    @Transactional
    public void delete(WaterExtractFullDto dto) {
        Optional<WaterExtractFull> deletedWaterExtractFull = waterExtractFullRepository.findBySurveyPointNumberAndLaborNumberAndIsNotArchive(dto.getSurveyPointNumber(), dto.getLaborNumber());

        deletedWaterExtractFull.ifPresent(entity -> {
            entity.setIsArchive(ENTITY_IS_ARCHIVE);

            waterExtractFullRepository.save(entity);

            Optional<WaterExtractFullResult> deletedWaterExtractFullResult = waterExtractFullResultRepository.findBySampleAndNotArchive(entity.getSample());

            deletedWaterExtractFullResult.ifPresent(resultEntity -> {
                resultEntity.setIsArchive(ENTITY_IS_ARCHIVE);

                waterExtractFullResultRepository.save(resultEntity);
            });
        });
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<WaterExtractFullDto> sourceDtos) {
        var emptyDtos = sourceDtos.stream()
                .filter(sourceDto -> Objects.isNull(sourceDto.getLaborNumber()) ||
                                Objects.equals(StringUtils.EMPTY, sourceDto.getLaborNumber()))
                                        .collect(Collectors.toList());

        sourceDtos.removeAll(emptyDtos);

        List<WaterExtractFullResult> waterExtractFullResultList = waterExtractFullResultRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<WaterExtractFullResultDto> resultDtos = waterExtractMapper.waterExtractFullResultToWaterExtractFullResultDto(waterExtractFullResultList);

        sourceDtos.forEach(sourceDto -> {
            Optional<WaterExtractFullResultDto> resultDto = resultDtos.stream()
                    .filter(dto -> Boolean.FALSE.equals(dto.getIsBlocked()) && Objects.equals(dto.getLaborNumber(), sourceDto.getLaborNumber()))
                    .findFirst();

            resultDto.ifPresent(waterExtractFullResultDto -> WaterExtractFullMethod.calculateWaterExtractFull(sourceDto, waterExtractFullResultDto));
        });

        waterExtractMapper.updateWaterExtractFullResultFromWaterExtractFullResultDto(waterExtractFullResultList, resultDtos);

        waterExtractFullResultRepository.saveAll(waterExtractFullResultList);
    }
}
