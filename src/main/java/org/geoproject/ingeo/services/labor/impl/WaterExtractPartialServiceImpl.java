package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialDto;
import org.geoproject.ingeo.dto.methodDtos.WaterExtractPartialResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.WaterExtractMapper;
import org.geoproject.ingeo.methods.labor.WaterExtractPartialMethod;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterExtractPartial;
import org.geoproject.ingeo.models.labor.WaterExtractPartialResult;
import org.geoproject.ingeo.repositories.labor.WaterExtractPartialRepository;
import org.geoproject.ingeo.repositories.labor.WaterExtractPartialResultRepository;
import org.geoproject.ingeo.services.labor.WaterExtractPartialResultService;
import org.geoproject.ingeo.services.labor.WaterExtractPartialService;
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
public class WaterExtractPartialServiceImpl implements WaterExtractPartialService {

    private final CurrentState currentState;
    private final WaterExtractMapper waterExtractMapper;

    private final WaterExtractPartialRepository waterExtractPartialRepository;
    private final WaterExtractPartialResultRepository waterExtractPartialResultRepository;

    private final WaterExtractPartialResultService waterExtractPartialResultService;

    @Override
    public List getDTOs(SurveyPoint surveyPoint) {
        List<WaterExtractPartial> entitiesByProject = waterExtractPartialRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<WaterExtractPartialDto> waterExtractPartialDtos = waterExtractMapper.waterExtractPartialToWaterExtractPartialDto(entitiesByProject);

        return waterExtractPartialDtos;
    }

    @Override
    public WaterExtractPartialDto getDto(WaterExtractPartial entity) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDto"));
    }

    @Override
    @Transactional
    public void updateFromDtos(List<WaterExtractPartialDto> dtos) {
        List<WaterExtractPartial> updatedObjects = waterExtractPartialRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<String> updatedLaborNumbers = updatedObjects.stream()
                .map(waterExtractPartial -> waterExtractPartial.getSample().getLaborNumber())
                .toList();

        List<WaterExtractPartialDto> oldDtos = dtos.stream()
                .filter(dto -> updatedLaborNumbers.contains(dto.getLaborNumber()))
                .toList();

        waterExtractMapper.updateWaterExtractPartialFromWaterExtractPartialDto(updatedObjects, oldDtos);

        waterExtractPartialRepository.saveAll(updatedObjects);

        updatedObjects.forEach(updatedObject -> {
                    var updatedResultObject = waterExtractPartialResultRepository.findBySampleAndNotArchive(updatedObject.getSample());

                    updatedResultObject.ifPresent(resultObject -> {
                        waterExtractMapper.updateWaterExtractPartialResultFromWaterExtractPartial(resultObject, updatedObject);

                        resultObject.setSamplingDate(updatedObject.getSamplingDate());

                        waterExtractPartialResultRepository.save(resultObject);
                    });
                });

        List<WaterExtractPartialDto> newDtos = dtos.stream()
                .filter(dto -> !updatedLaborNumbers.contains(dto.getLaborNumber()) && Objects.nonNull(dto.getLaborNumber()))
                .toList();

        if (!newDtos.isEmpty()) {
            List<WaterExtractPartial> newEntities = waterExtractMapper.waterExtractPartialDtoToWaterExtractPartial(newDtos);

            newEntities.forEach(newEntity -> {
                newEntity.setIsArchive(Boolean.FALSE);
                newEntity.setIsBlocked(Boolean.FALSE);
            });

            waterExtractPartialRepository.saveAll(newEntities);

            List<WaterExtractPartialResultDto> newWaterExtractPartialResultDtos = newEntities.stream()
                    .map(newEntity -> {
                        WaterExtractPartialResultDto waterExtractPartialResultDto = new WaterExtractPartialResultDto();

                        waterExtractPartialResultDto.setLaborNumber(newEntity.getSample().getLaborNumber());
                        waterExtractPartialResultDto.setSurveyPointNumber(newEntity.getSample().getSurveyPoint().getPointNumber());
                        waterExtractPartialResultDto.setDepthFrom(newEntity.getDepthFrom());
                        waterExtractPartialResultDto.setDepthTo(newEntity.getDepthTo());
                        waterExtractPartialResultDto.setSamplingDate(newEntity.getSamplingDate());

                        return waterExtractPartialResultDto;
                    })
                    .collect(Collectors.toList());

            waterExtractPartialResultService.create(newWaterExtractPartialResultDtos);
        }
    }

    @Override
    public void updateFromDTO(Sample sample, WaterExtractPartialDto sourceDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDTO"));
    }

    @Override
    public List<WaterExtractPartial> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getEntitiesBySurveyPoint"));
    }

    @Override
    public void create(List<WaterExtractPartialDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));
    }

    @Override
    @Transactional
    public void delete(WaterExtractPartialDto dto) {
        Optional<WaterExtractPartial> deletedWaterExtractPartial = waterExtractPartialRepository.findBySurveyPointNumberAndLaborNumberAndIsNotArchive(dto.getSurveyPointNumber(), dto.getLaborNumber());

        deletedWaterExtractPartial.ifPresent(entity -> {
            entity.setIsArchive(ENTITY_IS_ARCHIVE);

            waterExtractPartialRepository.save(entity);

            Optional<WaterExtractPartialResult> deletedWaterExtractPartialResult = waterExtractPartialResultRepository.findBySampleAndNotArchive(entity.getSample());

            deletedWaterExtractPartialResult.ifPresent(resultEntity -> {
                resultEntity.setIsArchive(ENTITY_IS_ARCHIVE);

                waterExtractPartialResultRepository.save(resultEntity);
            });
        });
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("checkForExistingLaborNumber"));
    }

    @Override
    public void calculate(List<WaterExtractPartialDto> sourceDtos) {
        var emptyDtos = sourceDtos.stream()
                .filter(sourceDto -> Objects.isNull(sourceDto.getLaborNumber()) ||
                                Objects.equals(StringUtils.EMPTY, sourceDto.getLaborNumber()))
                                        .collect(Collectors.toList());

        sourceDtos.removeAll(emptyDtos);

        List<WaterExtractPartialResult> waterExtractPartialResultList = waterExtractPartialResultRepository.findAllByProjectIdAndIsNotArchive(currentState.getCurrentProject().getId());

        List<WaterExtractPartialResultDto> resultDtos = waterExtractMapper.waterExtractPartialResultToWaterExtractPartialResultDto(waterExtractPartialResultList);

        sourceDtos.forEach(sourceDto -> {
            Optional<WaterExtractPartialResultDto> resultDto = resultDtos.stream()
//                    .filter(dto -> Boolean.FALSE.equals(dto.getIsBlocked()) && Objects.equals(dto.getLaborNumber(), sourceDto.getLaborNumber()))
                    .filter(dto -> Objects.equals(dto.getLaborNumber(), sourceDto.getLaborNumber()))
                    .findFirst();

            resultDto.ifPresent(waterExtractPartialResultDto -> WaterExtractPartialMethod.calculateWaterExtractPartial(sourceDto, waterExtractPartialResultDto));
        });

        waterExtractMapper.updateWaterExtractPartialResultFromWaterExtractPartialResultDto(waterExtractPartialResultList, resultDtos);

        waterExtractPartialResultRepository.saveAll(waterExtractPartialResultList);
    }
}
