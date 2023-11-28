package org.geoproject.ingeo.services.cameral.impl;

import org.geoproject.ingeo.dto.mainViewsDtos.EgeDto;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.EgeMapper;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.EgeRepository;
import org.geoproject.ingeo.services.cameral.EgeServise;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EgeServiseImpl implements EgeServise {

    private final EgeRepository egeRepository;
    private final EgeMapper egeMapper;

    @Override
    public List<Ege> getAll() {
        return egeRepository.findAll();
    }

    @Override
    public Ege getById(Long id) {
        return egeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ИГЭ не найден"));
    }

    @Override
    public Ege getBySample(Sample sample) {
        return null;
    }

    @Override
    @Transactional
    public void create(EgeDto dto) {
        throw new NotImplementedException("create метод не реализован");

    }

    @Override
    @Transactional
    public void create(List<Ege> egeList) {
        egeRepository.saveAll(egeList);
    }

    @Override
    @Transactional
    public void update(Ege ege) {
        egeRepository.save(ege);
    }

    @Override
    @Transactional
    public void delete(List<Ege> egeList) {
        egeRepository.deleteAll(egeList);
    }

    @Override
    public List<Ege> getByProject(Project project) {
        return egeRepository.findByProject(project);
    }

    @Override
    public List<Ege> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        throw new NotImplemented("getBySurveyPoint method in EgesServise not implemented");
    }

    @Override
    public Ege getByNumberAndProject(String number, Project project) {
        return egeRepository.findByNumberAndProject(number, project);
    }

    @Override
    public void delete(EgeDto object) {

    }

    @Override
    public List<EgeDto> getDtos(List<Ege> objects) {
        throw new NotImplementedException("getDtos метод не реализован");
    }

    @Override
    public void updateFromDtos(List<Ege> objects, List<EgeDto> dtos) {
        throw new NotImplemented("updateFromDtos not implemented");
    }

    @Override
    public EgeDto getDto(Ege ege) {

        var egeDto = egeMapper.egeToEgeDto(ege);

        return egeDto;
    }
}
