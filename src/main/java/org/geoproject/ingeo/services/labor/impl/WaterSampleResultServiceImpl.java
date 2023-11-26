package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.exceptions.UserInterfaceException;
import org.geoproject.ingeo.mapper.laborMethods.WaterSampleMapper;
import org.geoproject.ingeo.mapper.laborMethods.WaterSampleResultMapper;
import org.geoproject.ingeo.methods.labor.WaterChemicalAnalyzeMethod;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterSample;
import org.geoproject.ingeo.models.labor.WaterSampleResult;
import org.geoproject.ingeo.models.classificators.WaterGroup;
import org.geoproject.ingeo.repositories.labor.WaterSampleResultRepository;
import org.geoproject.ingeo.services.common.SurveyPointsService;
import org.geoproject.ingeo.services.labor.WaterSampleResultService;
import org.geoproject.ingeo.services.labor.WaterSampleService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_NOT_ARCHIVE;
import static org.geoproject.ingeo.constants.ServiceConstants.WATER_SAMPLE_IS_NOT_BLOCKED;

@Service
@RequiredArgsConstructor
public class WaterSampleResultServiceImpl implements WaterSampleResultService {

    private final WaterSampleResultRepository waterSampleResultRepository;

    private final WaterSampleResultMapper waterSampleResultMapper;
    private final WaterSampleMapper waterSampleMapper;

    private final CurrentState currentState;

    private final SurveyPointsService surveyPointsService;
    private final WaterSampleService waterSampleService;

    @Override
    public List<WaterSampleResultDto> getDTOs(SurveyPoint surveyPoint) {
        List<WaterSampleResult> entitiesByProject = waterSampleResultRepository.findAllByProject(currentState.getCurrentProject());

        return waterSampleResultMapper.waterSampleResultToWaterSampleResultDto(entitiesByProject);
    }

    @Override
    public WaterSampleResultDto getDto(WaterSampleResult entity) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getDto"));
    }

    @Override
    @Transactional
    public void updateFromDtos(List<WaterSampleResultDto> dtos) {
        WaterSampleResultDto waterSampleResultDto = new WaterSampleResultDto();

        dtos.remove(waterSampleResultDto);

        waterSampleResultDto.setLaborNumber("");

        dtos.forEach(dto -> System.out.println(dto.equals(waterSampleResultDto)));

        dtos.remove(waterSampleResultDto);

        checkIfFieldsAreFilled(dtos);

        List<SurveyPoint> surveyPoints = surveyPointsService.getAllByProject(currentState.getCurrentProject());

        List<String> dtosSurveyPointsNumbers = dtos.stream()
                .map(WaterSampleResultDto::getSurveyPointNumber)
                .toList();

        List<Long> surveyPointsIds = surveyPoints.stream()
                .filter(surveyPoint -> dtosSurveyPointsNumbers.contains(surveyPoint.getPointNumber()))
                .map(SurveyPoint::getId)
                .toList();

        List<WaterSampleResult> updatedObjects = waterSampleResultRepository.findAllBySurveyPointIdIn(surveyPointsIds);

        List<String> updatedWaterSamplesResultLaborNumbers = updatedObjects.stream()
                .map(WaterSampleResult::getLaborNumber)
                .toList();

        List<WaterSampleResultDto> oldDtos = dtos.stream()
                .filter(dto -> updatedWaterSamplesResultLaborNumbers.contains(dto.getLaborNumber()))
                .toList();

        waterSampleResultMapper.updateWaterSampleFromWaterSampleDto(updatedObjects, oldDtos);

        updatedObjects.forEach(waterSampleResult -> {
            WaterSample byLaborNumberAndSurveyPoint = waterSampleService.getByLaborNumberAndSurveyPoint(waterSampleResult.getLaborNumber(),
                    waterSampleResult.getSurveyPoint());

            byLaborNumberAndSurveyPoint.setBlockedFromWaterSampleResult(waterSampleResult.getIsBlocked());
        });


        waterSampleResultRepository.saveAll(updatedObjects);

        List<WaterSampleResultDto> newDtos = dtos.stream()
                .filter(dto -> !updatedWaterSamplesResultLaborNumbers.contains(dto.getLaborNumber()) && Objects.nonNull(dto.getLaborNumber()))
                .toList();

        Set<SurveyPoint> usedSurveyPoints = dtos.stream()
                .map(dto -> surveyPointsService.getByPointNumber(dto.getSurveyPointNumber(), currentState.getCurrentProject()))
                .collect(Collectors.toSet());

        usedSurveyPoints.remove(null);

        if (!newDtos.isEmpty()) {
            List<WaterSampleResult> waterSampleResultList = waterSampleResultMapper.waterSampleResultDtoToWaterSampleResult(newDtos, usedSurveyPoints);
            waterSampleResultList.forEach(waterSampleResult -> {
                waterSampleResult.setIsBlocked(WATER_SAMPLE_IS_NOT_BLOCKED);
                waterSampleResult.setWaterGroup(new WaterGroup(1L));
                waterSampleResult.setIsArchive(ENTITY_IS_NOT_ARCHIVE);
            });
            waterSampleResultRepository.saveAll(waterSampleResultList);

            waterSampleResultList.forEach(waterSampleService::create);
        }
    }

    private void checkIfFieldsAreFilled(List<WaterSampleResultDto> dtos) {
        for (int i = dtos.size() - 1; i >= 0; i--) {

            if (dtos.get(i).getLaborNumber() == null ||
                    Objects.equals(dtos.get(i).getLaborNumber(), StringUtils.EMPTY)) {
                throw new UserInterfaceException(ExceptionTypeEnum.LABOR_NUMBER_NOT_FILLED_EXCEPTION.getMessage());
            }
            if (dtos.get(i).getSurveyPointNumber() == null ||
                    Objects.equals(dtos.get(i).getSurveyPointNumber(), StringUtils.EMPTY)) {
                throw new UserInterfaceException(ExceptionTypeEnum.SURVEY_POINT_NUMBER_NOT_FILLED_EXCEPTION.getMessage());
            }

        }
    }

    @Override
    public void updateFromDTO(Sample sample, WaterSampleResultDto sourceDto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("updateFromDTO"));
    }

    @Override
    public List<WaterSampleResult> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("getEntitiesBySurveyPoint"));
    }

    @Override
    public void create(List<WaterSampleResultDto> dtos) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("create"));
    }

    @Override
    public void delete(WaterSampleResultDto dto) {
        throw new NotImplemented(ExceptionTypeEnum.METHOD_NOT_IMPLEMENTED_EXCEPTION.getExceptionMessage("delete"));
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        return waterSampleResultRepository.existsByLaborNumberAndSurveyPointProject(laborNumber, project) &&
                !Objects.equals(oldValue, laborNumber);
    }

    @Override
    @Transactional
    public void calculate(List<WaterSampleResultDto> dtos) {
        dtos.forEach(waterSampleResultDto -> {
            WaterSample waterSample = waterSampleService
                    .getByLaborNumberAndProjectId(
                            waterSampleResultDto.getLaborNumber(),
                            currentState.getCurrentProject().getId());

            WaterChemicalAnalyzeMethod.calculateAdditionalWaterChemistry(waterSampleResultDto);
        });

        updateFromDtos(dtos);
    }
}
