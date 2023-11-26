package org.geoproject.ingeo.services.methodViews.impl;

import org.geoproject.ingeo.dto.methodDtos.RingDensityDTO;
import org.geoproject.ingeo.exceptions.NotImplemented;
import org.geoproject.ingeo.mapper.laborMethods.RingDensityMapper;
import org.geoproject.ingeo.methods.labor.RingDensityMethod;
import org.geoproject.ingeo.models.labor.Density;
import org.geoproject.ingeo.models.Project;
import org.geoproject.ingeo.models.labor.RingDensity;
import org.geoproject.ingeo.models.Sample;
import org.geoproject.ingeo.models.labor.WaterContent;
import org.geoproject.ingeo.repositories.labor.RingDensityRepository;
import org.geoproject.ingeo.services.methodViews.DensityService;
import org.geoproject.ingeo.services.methodViews.RingDensityService;
import org.geoproject.ingeo.services.methodViews.WaterContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RingDensityServiceImpl implements RingDensityService {

    private final WaterContentService waterContentService;
    private final DensityService densityService;

    private final RingDensityRepository ringDensityRepository;

    private final RingDensityMapper ringDensityMapper;

    @Override
    public RingDensity getBySample(Sample sample) {
        Optional<RingDensity> bySample = ringDensityRepository.findBySample(sample);

        if (!bySample.isPresent()) {
            RingDensity newRingDensity = new RingDensity();

            ringDensityMapper.updateRingDensityFromSample(newRingDensity, sample);

            newRingDensity.setSample(sample);

            ringDensityRepository.save(newRingDensity);

            return newRingDensity;
        }

        return bySample.get();
    }

    @Override
    public List<RingDensity> getAllBySample(Sample sample) {
        throw new NotImplemented("getAllBySample method in RingDensityServiceImpl not implemented");
    }

    @Override
    public void update(RingDensity object) {
        throw new NotImplemented("update method in RingDensityService not implemented");
    }

    @Override
    public void updateFromDTO(RingDensityDTO ringDensityDTO, Sample sample) {
        RingDensity foundRingDensity = getBySample(sample);

        ringDensityMapper.updateRingDensity(foundRingDensity, ringDensityDTO);

        ringDensityRepository.save(foundRingDensity);
    }

    @Override
    public RingDensityDTO getDto(RingDensity ringDensity) {
        return ringDensityMapper.ringDensityToRingDensityDTO(ringDensity);
    }

    @Override
    public void calculate(Sample sample, RingDensityDTO ringDensityDTO) {
        WaterContent waterContent = waterContentService.getBySample(sample);
        Density density = densityService.getBySample(sample);

        RingDensityMethod.calculateRingDensityAndIndexes(ringDensityDTO,
                waterContent.getNaturalAverageWaterContent(),
                density.getAverageDensity());

        updateFromDTO(ringDensityDTO, sample);
    }

    @Override
    public RingDensity getBySampleAndNumber(Sample sample, Integer number) {
        throw new NotImplemented("getBySampleAndNumber method in RingDensityServiceImpl not implemented");
    }

    @Override
    public List<RingDensity> getByProject(Project currentProject) {
        throw new NotImplemented("getByProject method not implemented");
    }
}