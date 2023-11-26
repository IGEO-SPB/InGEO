package org.geoproject.ingeo.services.mainViews.impl;

import org.geoproject.ingeo.dto.mainViewsDtos.EgeDTO;
import org.geoproject.ingeo.exceptions.NotFoundException;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.models.Ege;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.SurveyPoint;
import org.geoproject.ingeo.repositories.EgesRepository;
import org.geoproject.ingeo.services.mainViews.EgesServise;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EgesServiseImpl implements EgesServise {

    private final EgesRepository egesRepository;

    @Override
    public List<Ege> getAll() {
        return egesRepository.findAll();
    }

    @Override
    public Ege getById(Long id) {
        return egesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ИГЭ не найден"));
    }

    @Override
    public Ege getBySample(Sample sample) {
        return null;
    }

    @Override
    @Transactional
    public void create(EgeDTO dto) {
        throw new NotImplementedException("create метод не реализован");

    }

    @Override
    @Transactional
    public void create(List<Ege> egeList) {
        egesRepository.saveAll(egeList);
    }

    @Override
    @Transactional
    public void update(Ege ege) {
        egesRepository.save(ege);
    }

    @Override
    @Transactional
    public void delete(List<Ege> egeList) {
        egesRepository.deleteAll(egeList);
    }

    @Override
    public List<Ege> getByProject(Project project) {
        return egesRepository.findByProject(project);
    }

    @Override
    public List<Ege> getBySurveyPoint(SurveyPoint surveyPoint, Sort laborNumber) {
        throw new NotImplemented("getBySurveyPoint method in EgesServise not implemented");
    }

    @Override
    public Ege getByNumberAndProject(String number, Project project) {
        return egesRepository.findByNumberAndProject(number, project);
    }

    @Override
    public void delete(EgeDTO object) {

    }

    @Override
    public List<EgeDTO> getDtos(List<Ege> objects) {
        throw new NotImplementedException("getDtos метод не реализован");
    }

    @Override
    public void updateFromDtos(List<Ege> objects, List<EgeDTO> dtos) {
        throw new NotImplemented("updateFromDtos not implemented");
    }
}
