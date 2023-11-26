package org.geoproject.ingeo.services.common.impl;

import org.geoproject.ingeo.dto.SampleDto;
import org.geoproject.ingeo.exceptions.ConflictException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.SampleMapper;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.SamplesRepository;
import org.geoproject.ingeo.services.common.SampleService;
import org.geoproject.ingeo.utils.CurrentState;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.geoproject.ingeo.constants.ServiceConstants.LABOR_NUMBER_FIELD;
import static org.geoproject.ingeo.constants.ServiceConstants.ZERO_LIST_SIZE;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private final SamplesRepository samplesRepository;
    private final SampleMapper sampleMapper;
    protected final CurrentState currentState;

    public List<Sample> getAll() {
        return samplesRepository.findAll();
    }

    @Override
    public Sample getById(Long id) {
        return null;
    }

    @Override
    public Sample getBySample(Sample sample) {
        return null;
    }

    @Override
    @Transactional
    public void create(SampleDto dto) {
        Sample newSample = sampleMapper.sampleDtoToSample(dto);
        
        samplesRepository.save(newSample);
    }

    @Override
    @Transactional
    public void create(List<Sample> sampleList) {

        samplesRepository.saveAll(sampleList);
    }

    @Override
    @Transactional
    public void update(Sample sample) {
        samplesRepository.save(sample);
    }

    @Override
    @Transactional
    public void delete(List<Sample> sampleList) {
        samplesRepository.deleteAll(sampleList);
    }

    @Override
    public List<Sample> getByProject(Project project) {
        return samplesRepository.findByProject(project);
    }

    @Override
    public List<Sample> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        return samplesRepository.findBySurveyPoint(surveyPoint, laborNumber);
    }

    @Override
    public Sample getByLaborNumber(String laborNumber) {
        return samplesRepository.findByLaborNumber(laborNumber)
                .orElseThrow(() -> new RuntimeException("Sample entity not found"));
    }

    @Override
    public Sample getBySurveyPointAndLaborNumber(String laborNumber) {
        return null;
    }

    @Override
    public void setFirstSampleForView() {
        List<Sample> bySurveyPoint = getBySurveyPoint(currentState.getSurveyPoint(), Sort.by(LABOR_NUMBER_FIELD));

        if (bySurveyPoint.size() == ZERO_LIST_SIZE) {
            throw new ConflictException("Выбранная точка исследования не содержит образцов.");
        }

        Sample sample = bySurveyPoint.get(NumberUtils.INTEGER_ZERO);

        currentState.setSample(sample);
    }

    @Override
    public void delete(SampleDto object) {
        throw new NotImplementedException("delete метод не реализован");
    }

    @Override
    public List<SampleDto> getDtos(List<Sample> objects) {
        throw new NotImplementedException("getDtos метод не реализован");
    }

    @Override
    public void updateFromDtos(List<Sample> objects, List<SampleDto> dtos) {
        throw new NotImplemented("updateFromDtos not implemented");
    }
}