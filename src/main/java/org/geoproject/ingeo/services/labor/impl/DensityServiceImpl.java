package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.methodDtos.DensityDTO;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.DensityMapper;
import org.geoproject.ingeo.methods.labor.DensityMethod;
import org.geoproject.ingeo.models.labor.Density;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.repositories.labor.DensityRepository;
import org.geoproject.ingeo.services.methodViews.DensityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DensityServiceImpl implements DensityService {

    private final DensityRepository densityRepository;
    private final DensityMapper densityMapper;

    @Override
    public Density getBySample(Sample sample) {
        Optional<Density> bySample = densityRepository.findBySample(sample);

        if (!bySample.isPresent()) {
            Density newDensity = new Density();

            densityMapper.updateDensityFromSample(newDensity, sample);

            densityRepository.save(newDensity);

            return newDensity;
        }

        return bySample.get();
    }

    @Override
    public List<Density> getAllBySample(Sample sample) {
        throw new NotImplemented("getAllBySample method in DensityServiceImpl not implemented");
    }


    @Override
    public void update(Density object) {
        throw new NotImplemented("update method in DensityService not implemented");
    }

    @Override
    public void updateFromDTO(DensityDTO densityDTO, Sample sample) {
        Density byLaborNumber = getBySample(sample);

        densityMapper.updateDensity(byLaborNumber, densityDTO);

        densityRepository.save(byLaborNumber);
    }

    @Override
    public DensityDTO getDto(Density density) {
        return densityMapper.densityToDensityDTO(density);
    }

    @Override
    public void calculate(Sample sample, DensityDTO densityDTO) {
        DensityMethod.calculateAverageDensity(densityDTO);
        updateFromDTO(densityDTO, sample);
    }

    @Override
    public Density getBySampleAndNumber(Sample sample, Integer number) {
        throw new NotImplemented("getBySampleAndNumber method in DensityServiceImpl not implemented");
    }

    @Override
    public List<Density> getByProject(Project currentProject) {
        throw new NotImplemented("getByProject method not implemented");
    }
}
