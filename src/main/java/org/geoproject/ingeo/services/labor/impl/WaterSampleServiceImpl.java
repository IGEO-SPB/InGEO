package org.geoproject.ingeo.services.labor.impl;

import org.geoproject.ingeo.dto.methodDtos.WaterSampleDto;
import org.geoproject.ingeo.dto.methodDtos.WaterSampleResultDto;
import org.geoproject.ingeo.exceptions.ExceptionTypeEnum;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.mapper.laborMethods.WaterSampleMapper;
import org.geoproject.ingeo.mapper.laborMethods.WaterSampleResultMapper;
import org.geoproject.ingeo.methods.labor.WaterChemicalAnalyzeMethod;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.models.labor.WaterSample;
import org.geoproject.ingeo.models.labor.WaterSampleResult;
import org.geoproject.ingeo.models.classificators.WaterGroup;
import org.geoproject.ingeo.repositories.labor.WaterSampleRepository;
import org.geoproject.ingeo.repositories.labor.WaterSampleResultRepository;
import org.geoproject.ingeo.services.labor.WaterSampleService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.geoproject.ingeo.constants.LaborMethodConstants.DEFAULT_DILUTION_RATIO;
import static org.geoproject.ingeo.constants.ServiceConstants.ENTITY_IS_ARCHIVE;

@Service
@RequiredArgsConstructor
public class WaterSampleServiceImpl implements WaterSampleService {

    private final WaterSampleRepository waterSampleRepository;
    private final WaterSampleResultRepository waterSampleResultRepository;

    private final WaterSampleMapper waterSampleMapper;
    private final WaterSampleResultMapper waterSampleResultMapper;

    private final CurrentState currentState;

    @Override
    public List getDTOs(SurveyPoint surveyPoint) {
        List<WaterSample> entitiesByProject = waterSampleRepository.findAllByProject(currentState.getCurrentProject());

        List<WaterSampleDto> waterSampleDtos = waterSampleMapper.waterSampleToWaterSampleDto(entitiesByProject);

        return waterSampleDtos;
    }

    @Override
    public WaterSampleDto getDto(WaterSample entity) {
        WaterSample foundWaterSample = waterSampleRepository.findById(entity.getId())
                .orElseThrow(() -> new RuntimeException("WaterSample entity not found"));

        return waterSampleMapper.waterSampleToWaterSampleDto(foundWaterSample);
    }

    @Override
    @Transactional
    public void updateFromDtos(List<WaterSampleDto> dtos) {
        List<WaterSample> updatedObjects = waterSampleRepository.findAllByProject(currentState.getCurrentProject());

        List<String> updatedWaterSamplesLaborNumbers = updatedObjects.stream()
                .map(WaterSample::getLaborNumber)
                .toList();

        List<WaterSampleDto> oldDtos = dtos.stream()
                .filter(dto -> updatedWaterSamplesLaborNumbers.contains(dto.getLaborNumber()))
                .toList();

        waterSampleMapper.updateWaterSampleFromWaterSampleDto(updatedObjects, oldDtos);

        waterSampleRepository.saveAll(updatedObjects);

        List<WaterSampleDto> newDtos = dtos.stream()
                .filter(dto -> !updatedWaterSamplesLaborNumbers.contains(dto.getLaborNumber()) && Objects.nonNull(dto.getLaborNumber()))
                .toList();

        if (!newDtos.isEmpty()) {
            List<WaterSample> entities = waterSampleMapper.waterSampleDtoToWaterSample(newDtos);

            waterSampleRepository.saveAll(entities);
        }
    }

    @Override
    @Transactional
    public void updateFromDTO(Sample sample, WaterSampleDto sourceDto) {
        WaterSample foundWaterSample = waterSampleRepository.findByLaborNumber(sourceDto.getLaborNumber())
                .orElseThrow(() -> new RuntimeException("WaterSample entity not found"));

        waterSampleMapper.updateWaterSampleFromWaterSampleDto(foundWaterSample, sourceDto);

        waterSampleRepository.save(foundWaterSample);
    }

    @Override
    public List<WaterSample> getEntitiesBySurveyPoint(SurveyPoint surveyPoint) {
        return waterSampleRepository.findBySurveyPoint(surveyPoint);
    }

    @Override
    public WaterSample getByLaborNumberAndProjectId(String laborNumber, Long projectId) {
        return waterSampleRepository.findByLaborNumberAndProjectId(laborNumber, projectId);
    }

    @Override
    @Transactional
    public void create(List<WaterSampleDto> dtos) {
        List<WaterSample> waterSamples = waterSampleMapper.waterSampleDtoToWaterSample(dtos);

        waterSampleRepository.saveAll(waterSamples);
    }

    @Override
    @Transactional
    public void create(WaterSampleResult waterSampleResult) {
        WaterSample waterSample = new WaterSample();

        waterSample.setLaborNumber(waterSampleResult.getLaborNumber());
        waterSample.setSurveyPoint(waterSampleResult.getSurveyPoint());

        waterSample.setRHCO3(DEFAULT_DILUTION_RATIO);
        waterSample.setRCO3(DEFAULT_DILUTION_RATIO);
        waterSample.setRCL(DEFAULT_DILUTION_RATIO);
        waterSample.setRNO2(DEFAULT_DILUTION_RATIO);
        waterSample.setRNO3(DEFAULT_DILUTION_RATIO);
        waterSample.setRCa(DEFAULT_DILUTION_RATIO);
        waterSample.setRMg(DEFAULT_DILUTION_RATIO);
        waterSample.setRNH4(DEFAULT_DILUTION_RATIO);
        waterSample.setRFe(DEFAULT_DILUTION_RATIO);
        waterSample.setRSiO2(DEFAULT_DILUTION_RATIO);
        waterSample.setRO2(DEFAULT_DILUTION_RATIO);
        waterSample.setRCO2sv(DEFAULT_DILUTION_RATIO);
        waterSample.setRCO2ag(DEFAULT_DILUTION_RATIO);
        waterSample.setRH2S(DEFAULT_DILUTION_RATIO);

        waterSampleRepository.save(waterSample);
    }

    @Override
    @Transactional
    public void delete(WaterSampleDto waterSampleDto) {
        WaterSample deletedWaterSample = waterSampleRepository.findByLaborNumber(waterSampleDto.getLaborNumber())
                .orElseThrow(() -> new NotFoundException(ExceptionTypeEnum.ENTITY_NOT_FOUND_EXCEPTION.getExceptionMessage("WaterSample")));
        //TODO: предусмотреть удаление связной сущности WaterSampleResult
        deletedWaterSample.setIsArchive(ENTITY_IS_ARCHIVE);

        waterSampleRepository.save(deletedWaterSample);
    }

    @Override
    public Boolean checkForExistingLaborNumber(String oldValue, String laborNumber, Project project) {
        return waterSampleRepository.existsByLaborNumberAndSurveyPointProject(laborNumber, project) &&
                !Objects.equals(oldValue, laborNumber);
    }

    @Override
    public void calculate(List<WaterSampleDto> dtos) {
        var waterSampleResultList = currentState.getCurrentProject().getSurveyPoints().stream()
                .flatMap(surveyPoint -> surveyPoint.getWaterSampleResultList().stream())
                .toList();

        dtos.forEach(dto -> {
            Optional<WaterSampleResult> waterSampleResultOptional =
                    waterSampleResultList.stream()
                            .filter(sample ->
                                    Boolean.FALSE.equals(sample.getIsBlocked()) &&
                                            sample.getLaborNumber()
                                                    .equals(dto.getLaborNumber())
                            )
                            .findFirst();

            WaterSampleResult waterSampleResult;

            if (waterSampleResultOptional.isPresent()) {
                waterSampleResult = waterSampleResultOptional.get();

                WaterSampleResultDto waterSampleResultDto = waterSampleResultMapper.waterSampleResultToWaterSampleResultDto(waterSampleResult);

                WaterChemicalAnalyzeMethod.calculateWaterChemistry(waterSampleResultDto, dto);
                waterSampleResultMapper.updateWaterSampleFromWaterSampleDto(waterSampleResult, waterSampleResultDto);
                waterSampleResult.setId(waterSampleResultDto.getId());
                waterSampleResult.setWaterGroup(new WaterGroup(1L));

                waterSampleResultRepository.save(waterSampleResult);
            }
        });
    }

    @Override
    public WaterSample getByLaborNumberAndSurveyPoint(String laborNumber, SurveyPoint surveyPoint) {
        return waterSampleRepository.findByLaborNumberAndSurveyPoint(laborNumber, surveyPoint);
    }
}
