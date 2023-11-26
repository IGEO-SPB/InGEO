package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.methodDtos.BoychenkoConeDTO;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.BoychenkoConeMapper;
import org.geoproject.ingeo.methods.labor.BoychenkoConeMethod;
import org.geoproject.ingeo.models.labor.BoychenkoCone;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.repositories.labor.BoychenkoConeRepository;
import org.geoproject.ingeo.services.methodViews.BoychenkoConeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoychenkoConeServiceImpl implements BoychenkoConeService {
    private final BoychenkoConeRepository boychenkoConeRepository;
    private final BoychenkoConeMapper boychenkoConeMapper;

    @Override
    public BoychenkoCone getBySample(Sample sample) {
        Optional<BoychenkoCone> bySample = boychenkoConeRepository.findBySample(sample);

        if (!bySample.isPresent()) {
            BoychenkoCone newBoychenkoCone = new BoychenkoCone();

            boychenkoConeMapper.updateBoychenkoConeFromSample(newBoychenkoCone, sample);

            boychenkoConeRepository.save(newBoychenkoCone);

            return newBoychenkoCone;
        }

        return bySample.get();
    }

    @Override
    public List<BoychenkoCone> getAllBySample(Sample sample) {
        throw new NotImplemented("getAllBySample method in BoychenkoConeServiceImpl not implemented");
    }

    @Override
    public void update(BoychenkoCone object) {
        throw new NotImplemented("update method in BoychenkoCone service not implemented");
    }

    @Override
    @Transactional
    public void updateFromDTO(BoychenkoConeDTO boychenkoConeDTO, Sample sample) {
        BoychenkoCone bySample = boychenkoConeRepository.findBySample(sample)
                .orElseThrow(() -> new RuntimeException("BoychenkoCone entity not found"));

        boychenkoConeMapper.updateBoychenkoCone(bySample, boychenkoConeDTO);

        boychenkoConeRepository.save(bySample);
    }

    @Override
    public BoychenkoConeDTO getDto(BoychenkoCone boychenkoCone) {
        return boychenkoConeMapper.boychenkoConeToBoychenkoConeDTO(boychenkoCone);
    }

    @Override
    public void calculate(Sample sample, BoychenkoConeDTO boychenkoConeDTO) {
        BoychenkoConeDTO calculatedBoychenkoConeDTO = BoychenkoConeMethod.calculateBoychenkoConeImmersionDepths(boychenkoConeDTO);
        updateFromDTO(calculatedBoychenkoConeDTO, sample);
    }

    @Override
    public BoychenkoCone getBySampleAndNumber(Sample sample, Integer number) {
        throw new NotImplemented("getBySampleAndNumber method in BoychenkoConeServiceImpl not implemented");
    }

    @Override
    public List<BoychenkoCone> getByProject(Project currentProject) {
        throw new NotImplemented("getByProject method not implemented");
    }
}
