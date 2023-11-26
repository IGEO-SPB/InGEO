package com.geoproject.igeo.services.classificators.impl;

import com.geoproject.igeo.models.SurveyPointsType;
import com.geoproject.igeo.repositories.SurveyPointsTypesRepository;
import com.geoproject.igeo.services.classificators.SurveyPointsTypesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyPointsTypesServiceImpl implements SurveyPointsTypesService {
    private final SurveyPointsTypesRepository surveyPointsTypesRepository;

    public SurveyPointsTypesServiceImpl(SurveyPointsTypesRepository surveyPointsTypesRepository) {
        this.surveyPointsTypesRepository = surveyPointsTypesRepository;
    }

    public SurveyPointsType findOne(int id) {
        Optional<SurveyPointsType> foundProject = surveyPointsTypesRepository.findById(id);
        return foundProject.orElse(null);
    }

    @Override
    public List<SurveyPointsType> findAll() {
        return surveyPointsTypesRepository.findAll();
    }

    @Override
    public SurveyPointsType findBySurveyTypeShortName(String surveyTypeShortName) {
        return surveyPointsTypesRepository.findBySurveyTypeShortName(surveyTypeShortName);
    }
}
