package org.geoproject.ingeo.services.cameral.impl;

import org.geoproject.ingeo.dto.mainViewsDtos.BoreholeLayerDTO;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.BoreholeLayerMapper;
import org.geoproject.ingeo.models.BoreholeLayer;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.BoreHoleLayerMainRepository;
import org.geoproject.ingeo.services.cameral.BoreHoleLayerMainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoreHoleLayerMainServiceImpl implements BoreHoleLayerMainService {

    private final BoreHoleLayerMainRepository boreHoleLayerMainRepository;
    private final BoreholeLayerMapper boreholeLayerMapper;

    @Override
    public List<BoreholeLayer> getAll() {
        return boreHoleLayerMainRepository.findAll();
    }

    @Override
    public BoreholeLayer getById(Long id) {
        return boreHoleLayerMainRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Слой не найден"));
    }

    @Override
    public BoreholeLayer getBySample(Sample sample) {
        return null;
    }

    @Override
    @Transactional
    public void create(BoreholeLayerDTO dto) {
        BoreholeLayer boreholeLayer = boreholeLayerMapper.boreholeLayerDtoToBoreholeLayer(dto);

        boreHoleLayerMainRepository.save(boreholeLayer);
    }

    @Override
    @Transactional
    public void create(List<BoreholeLayer> boreholeLayerList) {
        boreHoleLayerMainRepository.saveAll(boreholeLayerList);
    }

    @Override
    @Transactional
    public void update(BoreholeLayer boreholeLayer) {
        boreHoleLayerMainRepository.save(boreholeLayer);
    }

    @Override
    @Transactional
    public void delete(List<BoreholeLayer> boreholeLayerList) {
        boreHoleLayerMainRepository.deleteAll(boreholeLayerList);
    }

    @Override
    public List<BoreholeLayer> getByProject(Project project) {
        throw new NotImplemented("getByProject method in BoreHoleLayerMainService not implemented");
    }

    @Override
    public List<BoreholeLayer> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        return boreHoleLayerMainRepository.findBySurveyPoint(surveyPoint);
    }

    @Override
    public void delete(BoreholeLayerDTO object) {
        throw new NotImplementedException("delete метод не реализован");
    }

    @Override
    public List<BoreholeLayerDTO> getDtos(List<BoreholeLayer> objects) {
        throw new NotImplementedException("getDtos метод не реализован");
    }

    @Override
    public void updateFromDtos(List<BoreholeLayer> objects, List<BoreholeLayerDTO> dtos) {
        throw new NotImplemented("updateFromDtos not implemented");
    }
}